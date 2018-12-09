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

import java.io.IOException;

@Name("Unlock issue")
@Description("Unlock an issue")
@Examples({
        "command unlock:",
        "\ttrigger:",
        "\t\tset {_issues::*} to all issues of repository with name \"test\"",
        "\t\tunlock {_issues::1}"
})
@Since("1.0")

public class EffUnlockIssue extends Effect {

    static {
        Skript.registerEffect(EffUnlockIssue.class,
                "unlock [[skron] issue] %issue%");
    }

    private Expression<GHIssue> issue;

    @Override
    public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
        issue = (Expression<GHIssue>) expr[0];
        return true;
    }

    @Override
    protected void execute(Event e) {
        GHIssue i = issue.getSingle(e);
        try {
            i.unlock();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "unlock " + issue.getSingle(e).toString();
    }
}
