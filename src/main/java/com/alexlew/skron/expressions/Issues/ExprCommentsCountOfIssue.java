package com.alexlew.skron.expressions.Issues;

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
import org.kohsuke.github.GHIssueComment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Name("Comments count of issue")
@Description("Returns the number of comments of an issue")
@Examples({
        "set {_comments::*} to comments count of {_issue}"
})
@Since("1.0")


public class ExprCommentsCountOfIssue extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ExprCommentsCountOfIssue.class, Integer.class, ExpressionType.SIMPLE,
                "[the] comments count of %issue%",
                          "[the] %issue%'s comments count");
    }

    private Expression<GHIssue> issue;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        issue = (Expression<GHIssue>) expr[0];
        return true;
    }

    @Override
    protected Integer[] get( Event e ) {
        Integer count = issue.getSingle(e).getCommentsCount();
        return new Integer[] {count};
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "comments count";
    }

}

