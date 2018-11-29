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

@Name("Comment issue")
@Description("Comment an issue")
@Examples({
        "command comment:",
        "\ttrigger:",
        "\t\tcomment issue with id 2 from last repository with \"Hey bro, it's just a test, look this!\""
})
@Since("1.0")

public class EffCommentIssue extends Effect {

    static {
        Skript.registerEffect(EffCommentIssue.class,
                "comment [[skron] issue] %issue% with %string%");
    }

    private Expression<GHIssue> issue;
    private Expression<String> message;

    @Override
    public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
        issue = (Expression<GHIssue>) expr[0];
        message = (Expression<String>) expr[1];
        return true;
    }

    @Override
    protected void execute(Event e) {
        GHIssue i = issue.getSingle(e);
        String msg = message.getSingle(e);
        try {
            i.comment(msg);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "comment " + issue.getSingle(e).toString() + " with " + message.getSingle(e);
    }
}
