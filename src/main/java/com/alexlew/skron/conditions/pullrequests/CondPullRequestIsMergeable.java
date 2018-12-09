package com.alexlew.skron.conditions.pullrequests;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.kohsuke.github.GHPullRequest;

import java.io.IOException;

@Name("Pull request is mergeable")
@Description("Check if a pull request is mergeable")
@Examples({
        "command test:",
        "\ttrigger:",
        "\t\tif {_pullrequest} is mergeable:",
        "\t\t\tbroadcast \"Yeah, it is!\""
})
@Since("1.0")

public class CondPullRequestIsMergeable extends Condition {

    static {
        Skript.registerCondition(CondPullRequestIsMergeable.class,
                "%pullrequest% is meargeable"
                );
    }

    private Expression<GHPullRequest> pullrequest;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        pullrequest = (Expression<GHPullRequest>) expr[0] != null ? (Expression<GHPullRequest>) expr[0] : null;
        return true;
    }

    @Override
    public boolean check( Event e ) {
        GHPullRequest pr = pullrequest.getSingle(e) != null ? pullrequest.getSingle(e) : null;
        Boolean back = false;
        try {
            back = pr == null ? false : pr.getMergeable();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return back;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return pullrequest.getSingle(e) + " is mergeable";
    }

}
