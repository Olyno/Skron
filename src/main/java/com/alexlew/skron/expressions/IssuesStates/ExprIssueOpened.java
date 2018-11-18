package com.alexlew.skron.expressions.IssuesStates;

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
import org.kohsuke.github.GHIssueState;

@Name("Issues opened")
@Description("Return issues opened type")
@Examples({
        "set {_issues::*} to issues opened from last repository"
})
@Since("1.0")

public class ExprIssueOpened extends SimpleExpression<GHIssueState> {

    static {
        Skript.registerExpression(ExprIssueOpened.class, GHIssueState.class, ExpressionType.SIMPLE,
                          "[all] open issues");
    }

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        return true;
    }

    @Override
    protected GHIssueState[] get( Event e ) {
        return new GHIssueState[] {GHIssueState.OPEN};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends GHIssueState> getReturnType() {
        return GHIssueState.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "issues opened";
    }

}
