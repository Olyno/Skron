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
import org.kohsuke.github.GHPullRequestReview;
import org.kohsuke.github.PagedIterable;

import java.util.List;

@Name("Reviews of pull request")
@Description("Returns reviews of a pull request")
@Examples({
        "set {_reviews::*} to reviews of {_pull request}"
})
@Since("1.0")

public class ExprReviewsOfPullRequest extends SimpleExpression<GHPullRequestReview> {

    static {
        Skript.registerExpression(ExprReviewsOfPullRequest.class, GHPullRequestReview.class, ExpressionType.SIMPLE,
                "[the] [skron] reviews of %pullrequest%",
                "[the] [skron] %pullrequest%'s reviews");
    }

    private Expression<GHPullRequest> pullrequest;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        pullrequest = (Expression<GHPullRequest>) expr[0];
        return true;
    }

    @Override
    protected GHPullRequestReview[] get( Event e ) {
        GHPullRequest pr = pullrequest.getSingle(e);
        PagedIterable<GHPullRequestReview> reviews = pr.listReviews();
        List<GHPullRequestReview> r = reviews.asList();
        return r.toArray(new GHPullRequestReview[r.size()]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends GHPullRequestReview> getReturnType() {
        return GHPullRequestReview.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "reviews of " + pullrequest.getSingle(e);
    }

}

