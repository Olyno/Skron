package com.alexlew.skron.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Name("Comments of issue/commit")
@Description("Returns comments of an issue/commit")
@Examples({
        "set {_comments::*} to comments of {_issue}"
})
@Since("1.0")

public class ExprCommentsOfSomething extends SimpleExpression<Object> {

    static {
        Skript.registerExpression(ExprCommentsOfSomething.class, Object.class, ExpressionType.SIMPLE,
                "[the] [skron] comments of %object%",
                "[the] [skron] %object%'s comments");
    }

    private Expression<Object> object;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        object = (Expression<Object>) expr[0];
        return true;
    }

    @Override
    protected Object[] get( Event e ) {
        try {
            if (object.getSingle(e) instanceof GHIssue) {
                GHIssue issue = (GHIssue) object.getSingle(e);
                List<GHIssueComment> comments = new ArrayList<GHIssueComment>();
                comments = issue.getComments();
                return comments.toArray(new GHIssue[comments.size()]);

            } else if (object.getSingle(e) instanceof GHCommit) {
                GHCommit commit = (GHCommit) object.getSingle(e);
                PagedIterable<GHCommitComment> comments = commit.listComments();
                List<GHCommitComment> com = comments.asList();
                return com.toArray(new GHCommit[com.size()]);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;

    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.ADD) {
            return new Class[]{GHIssue.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        switch (mode) {
            case ADD:
                try {
                    for (Object o : delta) {
                        String value = (String) o;
                        if (object.getSingle(e) instanceof GHIssue)	{
                            GHIssue issue = (GHIssue) object.getSingle(e);
                            issue.comment(value);
                        } else if (object.getSingle(e) instanceof GHIssue)	{
                            GHCommit commit = (GHCommit) object.getSingle(e);
                            commit.createComment(value);

                        }
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isSingle() {
        return false;
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

