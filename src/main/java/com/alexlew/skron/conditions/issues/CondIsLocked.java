package com.alexlew.skron.conditions.issues;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;

@Name("Issue is locked")
@Description("Check if a issue is locked")
@Examples({
        "command lock:",
        "\ttrigger:",
        "\t\tif {_issue} is locked:",
        "\t\t\tbroadcast \"Yeah, this issue is locked!\""
})
@Since("1.0")

public class CondIsLocked extends Condition {

    static {
        Skript.registerCondition(CondIsLocked.class,
                "[skron] %issue% is locked"
                );
    }

    private Expression<GHIssue> issue;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        issue = (Expression<GHIssue>) expr[0];
        return true;
    }

    @Override
    public boolean check( Event e ) {
        return issue.getSingle(e).isLocked();
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return issue.getSingle(e) + " is locked";
    }

}
