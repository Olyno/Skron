package com.alexlew.skron.expressions.Issues;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Name("Comments of issue")
@Description("Returns comments of an issue")
@Examples({
        "set {_comments::*} to comments of {_issue}"
})
@Since("1.0")

public class ExprCommentsOfIssue extends SimpleExpression<GHIssueComment> {

    static {
        Skript.registerExpression(ExprCommentsOfIssue.class, GHIssueComment.class, ExpressionType.SIMPLE,
                "[the] comments of %issue%",
                          "[the] %issue%'s comments");
    }

    private Expression<GHIssue> issue;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        issue = (Expression<GHIssue>) expr[0];
        return true;
    }

    @Override
    protected GHIssueComment[] get( Event e ) {
        List<GHIssueComment> comments = new ArrayList<GHIssueComment>();
        try {
            comments = issue.getSingle(e)
                    .getComments();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        GHIssueComment[] commentsArray = new GHIssueComment[comments.size()];
        return commentsArray;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends GHIssueComment> getReturnType() {
        return GHIssueComment.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "comments";
    }

}

