package com.alexlew.skron.effects.issues;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHUser;

import java.io.IOException;

@Name("Assign issue")
@Description("Assign a issue to a user")
@Examples({
        "command assign:",
        "\ttrigger:",
        "\t\tset {_issues::*} to all issues of repository with name \"test\"",
        "\t\tassign {_issues::1} to github user \"AlexLew95\""
})
@Since("1.0")

public class EffAssignIssue extends Effect {

    static {
        Skript.registerEffect(EffAssignIssue.class,
                "assign [[skron] issue] %issue% to %githubuser%");
    }

    private Expression<GHIssue> issue;
    private Expression<GHUser> user;

    @Override
    public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
        issue = (Expression<GHIssue>) expr[0];
        user = (Expression<GHUser>) expr[1];
        return true;
    }

    @Override
    protected void execute(Event e) {
        GHIssue i = issue.getSingle(e);
        GHUser u = user.getSingle(e);
        try {
            i.assignTo(u);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public String toString(Event e, boolean debug) {
        try {
            return "assign " + issue.getSingle(e).toString() +  " to " + user.getSingle(e).getName();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
