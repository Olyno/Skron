package com.alexlew.skron.expressions.issues;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHUser;

import java.io.IOException;
import java.util.Arrays;

@Name("Assignee of Issue")
@Description("Returns the assignee users of an issue.")
@Examples({
        "add github user \"AlexLew95\" to assignee users of the issue"
})
@Since("1.0")

public class ExprAssigneeOfIssue extends SimpleExpression<GHUser> {

    static {
        Skript.registerExpression(ExprAssigneeOfIssue.class, GHUser.class, ExpressionType.SIMPLE,
                "assignee [user[s]] of %issue%",
                "%issue%'s assignee [user[s]]"
        );
    }

    private Expression<GHIssue> issue;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        issue = (Expression<GHIssue>) expr[0];
        return true;
    }

    @Override
    protected GHUser[] get( Event e ) {
        Object[] array = issue.getSingle(e).getAssignees().toArray();
        GHUser[] users = Arrays.copyOf(array, array.length, GHUser[].class);
        return users;
    }

    @Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.ADD || mode == ChangeMode.REMOVE || mode == ChangeMode.REMOVE_ALL) {
            return new Class[]{GHIssue.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, ChangeMode mode) {
        switch (mode) {
            case ADD:
                for (GHUser user : (GHUser[]) delta) {
                    try {
                        issue.getSingle(e).addAssignees(user);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                break;
            case REMOVE:
                for (GHUser user : (GHUser[]) delta) {
                    try {
                        issue.getSingle(e).removeAssignees(user);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                break;
            case REMOVE_ALL:
                for (GHUser user : issue.getSingle(e).getAssignees()) {
                    try {
                        issue.getSingle(e).removeAssignees(user);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                break;
            default:
                break;
            }
        }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends GHUser> getReturnType() {
        return GHUser.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "assignee users " + issue.getSingle(e).getAssignees().toString();
    }

}
