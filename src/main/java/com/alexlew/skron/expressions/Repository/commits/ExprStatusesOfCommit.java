package com.alexlew.skron.expressions.Repository.commits;

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
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommitStatus;
import org.kohsuke.github.PagedIterable;

import java.io.IOException;
import java.util.List;

@Name("Statuses of issue")
@Description("Returns statuses of a commit")
@Examples({
        "set {_statuses::*} to statuses of {_commit}"
})
@Since("1.0")

public class ExprStatusesOfCommit extends SimpleExpression<GHCommitStatus> {

    static {
        Skript.registerExpression(ExprStatusesOfCommit.class, GHCommitStatus.class, ExpressionType.SIMPLE,
                "[the] [skron] statuses of %commit%",
                "[the] [skron] %commit%'s statuses");
    }

    private Expression<GHCommit> com;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        com = (Expression<GHCommit>) expr[0];
        return true;
    }

    @Override
    protected GHCommitStatus[] get( Event e ) {
        GHCommit commit = com.getSingle(e);
        try {
            PagedIterable<GHCommitStatus> status = commit.listStatuses();
            List<GHCommitStatus> stats = status.asList();
            return stats.toArray(new GHCommitStatus[stats.size()]);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends GHCommitStatus> getReturnType() {
        return GHCommitStatus.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "statuses of " + com.getSingle(e);
    }

}

