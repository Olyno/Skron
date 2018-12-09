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
import org.kohsuke.github.GHPullRequestFileDetail;
import org.kohsuke.github.PagedIterable;

import java.util.List;

@Name("Files of pull request")
@Description("Returns files of a pull request")
@Examples({
        "set {_files::*} to files of {_pull request}"
})
@Since("1.0")

public class ExprFilesOfPullRequest extends SimpleExpression<GHPullRequestFileDetail> {

    static {
        Skript.registerExpression(ExprFilesOfPullRequest.class, GHPullRequestFileDetail.class, ExpressionType.SIMPLE,
                "[the] [skron] files of %pullrequest%",
                "[the] [skron] %pullrequest%'s files");
    }

    private Expression<GHPullRequest> pullrequest;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        pullrequest = (Expression<GHPullRequest>) expr[0];
        return true;
    }

    @Override
    protected GHPullRequestFileDetail[] get( Event e ) {
        GHPullRequest pr = pullrequest.getSingle(e);
        PagedIterable<GHPullRequestFileDetail> files = pr.listFiles();
        List<GHPullRequestFileDetail> f = files.asList();
        return f.toArray(new GHPullRequestFileDetail[f.size()]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends GHPullRequestFileDetail> getReturnType() {
        return GHPullRequestFileDetail.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "files of " + pullrequest.getSingle(e);
    }

}

