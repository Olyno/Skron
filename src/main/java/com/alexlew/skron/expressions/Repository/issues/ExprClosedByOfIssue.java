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
import com.alexlew.skron.effects.EffCreateRepository;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;

import java.io.IOException;

@Name("Close by of issue")
@Description("Returns the user who closed the issue")
@Examples({
        "set {_closedby} to who closed {_issue}"
})
@Since("1.0")

public class ExprClosedByOfIssue extends SimpleExpression<GHUser> {

    static {
        Skript.registerExpression(ExprClosedByOfIssue.class, GHUser.class,
                ExpressionType.SIMPLE, "[the] [skron] [[github] user] who closed %issue%");
    }

    private Expression<GHIssue> issue;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        issue = (Expression<GHIssue>) expr[0];
        return true;
    }

    @Override
    protected GHUser[] get( Event e ) {
        try {
            return new GHUser[] {issue.getSingle(e).getClosedBy()};
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
    public Class<? extends GHUser> getReturnType() {
        return GHUser.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "closed by ";
    }
}
