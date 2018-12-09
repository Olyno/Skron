package com.alexlew.skron.expressions;

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
import com.alexlew.skron.Skron;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHUser;

import java.io.IOException;

@Name("Close by of issue/pull request")
@Description("Returns the user who closed the issue/pull request")
@Examples({
        "set {_closedby} to who closed {_issue}"
})
@Since("1.0")

public class ExprClosedByOfSomething extends SimpleExpression<GHUser> {

    static {
        Skript.registerExpression(ExprClosedByOfSomething.class, GHUser.class,
                ExpressionType.SIMPLE, "[the] [skron] [[github] user] who closed %object%");
    }

    private Expression<Object> object;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        object = (Expression<Object>) expr[0];
        return true;
    }

    @Override
    protected GHUser[] get( Event e ) {
        try {
            if (object != null) {
                if (object.getSingle(e) instanceof GHIssue) {
                    GHIssue issue = (GHIssue) object.getSingle(e);
                    return new GHUser[] {issue.getClosedBy()};
                } else if (object.getSingle(e) instanceof GHPullRequest) {
                    GHPullRequest pr = (GHPullRequest) object.getSingle(e);
                    return new GHUser[] {pr.getClosedBy()};
                } else {
                    Skron.error(object.getSingle(e) + " is not a issue or a pull request to get who closed it: " + object.getSingle(e).getClass());
                }
            }
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
        return "user who closed " + object.getSingle(e);
    }
}
