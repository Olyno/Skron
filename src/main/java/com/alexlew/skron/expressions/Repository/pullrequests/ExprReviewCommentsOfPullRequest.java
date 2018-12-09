package com.alexlew.skron.expressions.Repository.pullrequests;

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
import org.bukkit.event.Event;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHPullRequestReviewComment;
import org.kohsuke.github.PagedIterable;

import java.io.IOException;
import java.util.List;

@Name("Review comments of pull request")
@Description("Returns review comments of a pull request")
@Examples({
        "set {_review comments::*} to review comments of {_pull request}"
})
@Since("1.0")

public class ExprReviewCommentsOfPullRequest extends SimpleExpression<GHPullRequestReviewComment> {

    static {
        Skript.registerExpression(ExprReviewCommentsOfPullRequest.class, GHPullRequestReviewComment.class, ExpressionType.SIMPLE,
                "[the] [skron] review comments of %pullrequest%",
                "[the] [skron] %pullrequest%'s review comments");
    }

    private Expression<GHPullRequest> pullrequest;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        pullrequest = (Expression<GHPullRequest>) expr[0];
        return true;
    }

    @Override
    protected GHPullRequestReviewComment[] get( Event e ) {
        GHPullRequest pr = pullrequest.getSingle(e);
        PagedIterable<GHPullRequestReviewComment> comments = null;
        try {
            comments = pr.listReviewComments();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        List<GHPullRequestReviewComment> c = comments.asList();
        return c.toArray(new GHPullRequestReviewComment[c.size()]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends GHPullRequestReviewComment> getReturnType() {
        return GHPullRequestReviewComment.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "review comments of " + pullrequest.getSingle(e);
    }

}

