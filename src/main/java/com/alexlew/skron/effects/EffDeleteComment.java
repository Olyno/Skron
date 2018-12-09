package com.alexlew.skron.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.alexlew.skron.Skron;
import org.bukkit.event.Event;
import org.kohsuke.github.GHCommitComment;
import org.kohsuke.github.GHIssueComment;

import java.io.IOException;

@Name("Comment commit")
@Description("Comment a commit")
@Examples({
        "command comment:",
        "\ttrigger:",
        "\t\tdelete comment {_comment}"
})
@Since("1.0")

public class EffDeleteComment extends Effect {

    static {
        Skript.registerEffect(EffDeleteComment.class,
                "delete [skron] comment %object%");
    }

    public static Object lastDeletedComment;

    private Expression<Object> comment;

    @Override
    public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
        comment = (Expression<Object>) expr[0];
        return true;
    }

    @Override
    protected void execute(Event e) {
        if (comment != null) {
            try {
                if (comment.getSingle(e) instanceof GHIssueComment) {
                    GHIssueComment com = (GHIssueComment) comment.getSingle(e);
                    com.delete();
                    lastDeletedComment = com;
                } else if (comment.getSingle(e) instanceof GHCommitComment) {
                    GHCommitComment com = (GHCommitComment) comment.getSingle(e);
                    com.delete();
                    lastDeletedComment = com;
                } else {
                    Skron.error(comment.getSingle(e).toString() + " is not a type comment, but a type " + comment.getSingle(e).getClass());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    @Override
    public String toString(Event e, boolean debug) {
        return "delete comment " + comment.getSingle(e);
    }
}
