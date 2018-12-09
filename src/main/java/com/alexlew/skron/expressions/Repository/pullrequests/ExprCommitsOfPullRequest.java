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
import org.kohsuke.github.GHPullRequestCommitDetail;
import org.kohsuke.github.PagedIterable;

import java.util.List;

@Name("Commits of pull request")
@Description("Returns commits of a pull request")
@Examples({
        "set {_commits::*} to commits of {_pull request}"
})
@Since("1.0")

public class ExprCommitsOfPullRequest extends SimpleExpression<GHPullRequestCommitDetail> {

    static {
        Skript.registerExpression(ExprCommitsOfPullRequest.class, GHPullRequestCommitDetail.class, ExpressionType.SIMPLE,
                "[the] [skron] commits of %pullrequest%",
                "[the] [skron] %pullrequest%'s commits");
    }

    private Expression<GHPullRequest> pullrequest;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        pullrequest = (Expression<GHPullRequest>) expr[0];
        return true;
    }

    @Override
    protected GHPullRequestCommitDetail[] get( Event e ) {
        GHPullRequest pr = pullrequest.getSingle(e);
        PagedIterable<GHPullRequestCommitDetail> commits = pr.listCommits();
        List<GHPullRequestCommitDetail> c = commits.asList();
        return c.toArray(new GHPullRequestCommitDetail[c.size()]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends GHPullRequestCommitDetail> getReturnType() {
        return GHPullRequestCommitDetail.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "commits of " + pullrequest.getSingle(e);
    }

}

