package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alexlew.skron.effects.EffLogin;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Name("Issues of repository")
@Description("Returns the issues of a repository")
@Examples({
        "set {_issues::*} to issues from last repository"
})
@Since("1.0")

public class ExprIssuesOfRepository extends SimpleExpression<GHIssue> {

    static {
        Skript.registerExpression(ExprIssuesOfRepository.class, GHIssue.class,
                ExpressionType.SIMPLE, "%issuestate% (from|of) %repository%");
    }

    private Expression<GHIssueState> issueType;
    private Expression<Repository> repository;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        issueType = (Expression<GHIssueState>) expr[0];
        repository = (Expression<Repository>) expr[1];
        return true;
    }

    @Override
    protected GHIssue[] get( Event e ) {
        List<GHIssue> issues = new ArrayList<GHIssue>();
        try {
            issues = EffLogin.account
                    .getRepository(repository.getSingle(e).getName())
                    .getIssues(issueType.getSingle(e));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        GHIssue[] issuesArray = new GHIssue[issues.size()];
        return issuesArray;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends GHIssue> getReturnType() {
        return GHIssue.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "issues containing";
    }

}
