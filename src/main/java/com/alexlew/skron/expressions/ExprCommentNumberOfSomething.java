package com.alexlew.skron.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alexlew.skron.Skron;
import org.bukkit.event.Event;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.List;

@Name("Comments number of issue/commit")
@Description("Returns the comment number X from comments of an issue/commit")
@Examples({
        "set {_comment} to comment 5 of {_issue}"
})
@Since("1.0")

public class ExprCommentNumberOfSomething extends SimpleExpression<Object> {

    static {
        Skript.registerExpression(ExprCommentNumberOfSomething.class, Object.class, ExpressionType.SIMPLE,
                "[the] [skron] comment %integer% of %object%",
                          "[the] [skron] %object%'s comment %integer%");
    }

    private Expression<Object> object;
    private Expression<Integer> number;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        if (matchedPattern == 0) {
            number = (Expression<Integer>) expr[0];
            object = (Expression<Object>) expr[1];
        } else {
            number = (Expression<Integer>) expr[1];
            object = (Expression<Object>) expr[0];
        }
        return true;
    }

    @Override
    protected Object[] get( Event e ) {
        if (object == null || number == null) {
            return null;
        }
        Integer n = number.getSingle(e);
        Integer i = 0;
        try {
            if (object.getSingle(e) instanceof GHIssue) {
                GHIssue issue = (GHIssue) object.getSingle(e);
                List<GHIssueComment> comments;
                comments = issue.getComments();
                if (n <= comments.size()) {
                    for (GHIssueComment c : comments.toArray(new GHIssueComment[comments.size()])) {
                        if ((i + 1) == n) {
                            return new GHIssueComment[] {c};
                        } else {
                            i++;
                        }

                    }
                } else {
                    Skron.error("There are only " + comments.size() + " comments, not " + number.getSingle(e));
                }


            } else if (object.getSingle(e) instanceof GHCommit) {
                GHCommit commit = (GHCommit) object.getSingle(e);
                PagedIterable<GHCommitComment> comments = commit.listComments();
                List<GHCommitComment> com = comments.asList();
                if (n <= com.size()) {
                    for (GHCommitComment c : com.toArray(new GHCommitComment[com.size()])) {
                        if ((i + 1) == n) {
                            return new GHCommitComment[] {c};
                        } else {
                            i++;
                        }
                    }
                } else {
                    Skron.error("There are only " + com.size() + " comments, not " + number.getSingle(e));
                }

            } else {
                Skron.error(object.getSingle(e) + " is not a issue or commit to get a comment from its id: " + object.getSingle(e).getClass());
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;

    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Object> getReturnType() {
        return Object.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "comments";
    }

}

