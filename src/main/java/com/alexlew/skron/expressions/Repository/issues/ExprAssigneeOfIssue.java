package com.alexlew.skron.expressions.Repository.issues;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alexlew.skron.types.Issue;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHUser;

import java.io.IOException;
import java.util.Arrays;

@Name("Assignee of Issue")
@Description("Returns the assignee users of an issue. Can be set in a issue builder scope")
@Examples({
        "# outside a issue builder scope",
        "add github user \"AlexLew95\" to assignee users of {_issue}",
        "# inside a issue builder scope",
        "make new issue:",
        "\tset assignee of issue builder to github user \"AlexLew95\""
})
@Since("1.0")

public class ExprAssigneeOfIssue extends SimpleExpression<GHUser> {

    static {
        Skript.registerExpression(ExprAssigneeOfIssue.class, GHUser.class, ExpressionType.SIMPLE,
                "[skron] assignee[s] [user[s]] of %object%",
                "%object%'s [skron] assignee[s] [user[s]]"
        );
    }

    private Expression<Object> issue;
    private Boolean alone = true;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        issue = (Expression<Object>) expr[0];
        return true;
    }

    @Override
    protected GHUser[] get( Event e ) {
        if (issue.getSingle(e) instanceof GHIssue) {
            GHIssue i = (GHIssue) issue.getSingle(e);
            Object[] array = i.getAssignees().toArray();
            GHUser[] users = Arrays.copyOf(array, array.length, GHUser[].class);
            alone = false;
            return users;
        } else if (issue.getSingle(e) instanceof Issue) {
            GHIssue i = (GHIssue) issue.getSingle(e);
            try {
                return new GHUser[] {i.getAssignee()};
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.ADD || mode == ChangeMode.REMOVE || mode == ChangeMode.REMOVE_ALL) {
            return new Class[]{GHIssue.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, ChangeMode mode) {
        switch (mode) {
            case SET:
                if (issue.getSingle(e) instanceof GHIssue) {
                    try {
                        GHIssue i = (GHIssue) issue.getSingle(e);
                        i.assignTo((GHUser) delta[0]);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (issue.getSingle(e) instanceof Issue) {
                    Issue i = (Issue) issue.getSingle(e);
                    i.setAssignee((GHUser) delta[0]);
                }

            case ADD:
                if (issue.getSingle(e) instanceof GHIssue) {
                    for (GHUser user : (GHUser[]) delta) {
                        try {
                            GHIssue i = (GHIssue) issue.getSingle(e);
                            i.addAssignees(user);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                break;
            case REMOVE:
                if (issue.getSingle(e) instanceof GHIssue) {
                    for (GHUser user : (GHUser[]) delta) {
                        try {
                            GHIssue i = (GHIssue) issue.getSingle(e);
                            i.removeAssignees(user);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                break;
            case REMOVE_ALL:
                if (issue.getSingle(e) instanceof GHIssue) {
                    GHIssue i = (GHIssue) issue.getSingle(e);
                    for (GHUser user : i.getAssignees()) {
                        try {
                            i.removeAssignees(user);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                break;
            case DELETE:
                if (issue.getSingle(e) instanceof Issue) {
                    Issue i = (Issue) issue.getSingle(e);
                    i.setAssignee(null);
                }
            default:
                break;
        }
    }

    @Override
    public boolean isSingle() {
        return alone;
    }

    @Override
    public Class<? extends GHUser> getReturnType() {
        return GHUser.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        if (issue.getSingle(e) instanceof GHIssue) {
            GHIssue i = (GHIssue) issue.getSingle(e);
            return "assignee users " + i.getAssignees().toString();
        } else if (issue.getSingle(e) instanceof Issue) {
            Issue i = (Issue) issue.getSingle(e);
            return "assignee user " + i.getAssignee().toString();
        } else {
            return null;
        }

    }

}