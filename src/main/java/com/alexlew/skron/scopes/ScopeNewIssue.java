package com.alexlew.skron.scopes;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.alexlew.skron.types.Issue;
import com.alexlew.skron.types.Repository;
import com.alexlew.skron.util.EffectSection;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;

@Name("Scope Issue Creation")
@Description("Scope for issue creation")
@Examples({
        "make new issue:",
        "\tset the repository of the issue builder to repository \"My Repository\"",
        "\tset the title of the issue builder to \"OwO a new issue\"",
        "\tset the body of the issue builder to \"Look my issue, how my issue is beautiful?\"",
        "\tset the label of the issue builder to \"Test\"",
        "\tset the assignee user of the issue builder to github user \"AlexLew95\"",
        "\tcreate the issue builder"
})
@Since("1.0")

public class ScopeNewIssue extends EffectSection {

    public static Issue lastIssue;

    static {
        Skript.registerCondition(ScopeNewIssue.class,
                "(make|do|create) [a] [new] [skron] issue");
    }

    @Override
    public boolean init( Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        if (checkIfCondition())
            return false;
        if (!hasSection()) {
            Skript.error("A issue creation scope is useless without any content!");
            return false;
        }
        loadSection(true);
        return true;
    }

    @Override
    protected void execute( Event e ) {
        lastIssue = new Issue();
        runSection(e);
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "make new issue";
    }
}
