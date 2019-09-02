package com.olyno.skron.skript.scopes;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.olyno.skron.util.EffectSection;
import com.olyno.skron.util.classes.IssueBuilder;
import org.bukkit.event.Event;

import java.util.HashMap;

@Name("Scope Issue Creation")
@Description("Scope for issue creation")
@Examples({
        ""
})
@Since("1.0.0")

public class ScopeIssueBuilderCreation extends EffectSection {

    public static IssueBuilder lastIssueBuilder;
    public static HashMap<String, IssueBuilder> issuesBuilder = new HashMap<>();

    static {
        Skript.registerCondition(ScopeIssueBuilderCreation.class,
                "(make|do|create) [new] issue [builder] [((with|as) name|named)] %string%"
        );
    }

    private Expression<String> issueName;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (checkIfCondition()) {
            return false;
        }
        if (!hasSection()) {
            Skript.error("An issue creation scope is useless without any content!");
            return false;
        }
        issueName = (Expression<String>) expr[0];
        loadSection(true);
        return true;
    }

    @Override
    protected void execute(Event e) {
        String name = issueName.getSingle(e);
        IssueBuilder issue = new IssueBuilder(name);
        issuesBuilder.put(name, issue);
        lastIssueBuilder = issue;
        runSection(e);
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "create new issue with name \"" + issueName.toString(e, debug) + "\"";
    }

}
