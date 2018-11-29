package com.alexlew.skron.scopes;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.alexlew.skron.util.EffectSection;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;

@Name("Scope COmmit Creation")
@Description("Scope for commit creation")
@Examples({
        "make new commit:",
        "\tset the repository of the commit to repository \"My Repository\"",
        "\tset the author of the commit to author with name \"AlexLew95\" and email \"myemail@gmail.com\"",
        "\tset the committer of the commit to committer with name \"AlexLew95\" and email \"myemail@gmail.com\"",
        "\tset the message of the commit to github user \"AlexLew95\"",
        "\tcreate the issue"
})
@Since("1.0")

public class ScopeNewCommit extends EffectSection {

    public static GHIssue lastIssue;

    static {
        Skript.registerCondition(ScopeNewCommit.class,
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
        lastIssue = new GHIssue();
        runSection(e);
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "make new issue";
    }
}
