package com.alexlew.skron.expressions.Repository.issues;


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
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

@Name("Issue with id")
@Description("Return an issue from its id")
@Examples({
        "command issue:",
        "\ttrigger:",
        "\t\tset {_issue} to issue with id 5 from last repository"
})
@Since("1.0")

public class ExprIssueWithId extends SimpleExpression<GHIssue> {

    static {
        Skript.registerExpression(ExprIssueWithId.class, GHIssue.class, ExpressionType.SIMPLE,
                "[the] [skron] issue [with id] %integer% (of|from) %repository%");
    }

    private Expression<Integer> issueId;
    private Expression<GHRepository> repository;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        issueId = (Expression<Integer>) expr[0];
        repository = (Expression<GHRepository>) expr[1];
        return true;
    }

    @Override
    protected GHIssue[] get( Event e ) {
        try {
            return new GHIssue[] {repository.getSingle(e).getIssue(issueId.getSingle(e))};
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
    public Class<? extends GHIssue> getReturnType() {
        return GHIssue.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "the issue with id " + issueId.getSingle(e);
    }

}
