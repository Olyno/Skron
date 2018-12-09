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
import com.alexlew.skron.scopes.ScopeNewComment;
import com.alexlew.skron.types.CommentType;
import org.bukkit.event.Event;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommitComment;
import org.kohsuke.github.GHIssue;

import java.io.IOException;

@Name("Comment commit")
@Description("Comment a commit")
@Examples({
        "command comment:",
        "\ttrigger:",
        "\t\tcomment {_comment}"
})
@Since("1.0")

public class EffComment extends Effect {

    static {
        Skript.registerEffect(EffComment.class,
                "[skron] comment [%-commenttype%]");
    }

    public static Object lastComment;

    private Expression<CommentType> comment;

    @Override
    public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
        comment = (Expression<CommentType>) expr[0];
        return true;
    }

    @Override
    protected void execute(Event e) {
        CommentType com = null;
        if (comment != null) {
            com = comment.getSingle(e);
        }
        CommentType builder = com != null ?
                com : ScopeNewComment.lastCommentBuilder;
        GHCommitComment validation = null;
        String body = builder.getBody();
        try {
            if (builder.getType() instanceof GHIssue) {
                GHIssue issue = (GHIssue) builder.getType();
                issue.comment(body);
            } else if (builder.getType() instanceof GHCommit) {
                GHCommit commit = (GHCommit) builder.getType();
                String path = builder.getPath();
                Integer line = builder.getLine();
                Integer position = builder.getPosition();
                if (path != null && line != null && position != null) {
                    validation = commit.createComment(body, path, line, position);
                }
                if (validation == null) {
                    lastComment = commit.createComment(body);
                } else {
                    lastComment = validation;
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "comment";
    }
}
