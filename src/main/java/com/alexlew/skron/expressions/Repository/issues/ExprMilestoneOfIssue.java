package com.alexlew.skron.expressions.Repository.issues;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Issue;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHMilestone;
import org.kohsuke.github.GHUser;

import java.io.IOException;

@Name("Milestone of Issue")
@Description("Returns the milestone of an issue. Can be set in a issue builder")
@Examples({
        "# outside a issue builder scope",
        "set {_milestone} to milestone of {_issue}",
        "# inside a issue builder scope",
        "make new issue:",
        "\tset milestone of issue builder to {_milestone}"
})
@Since("1.0")

public class ExprMilestoneOfIssue extends SimplePropertyExpression<Object, GHMilestone> {

    static {
        register(ExprMilestoneOfIssue.class, GHMilestone.class,
                "[the] [skron] milestone", "issue");
    }

    @Override
    public GHMilestone convert(Object issue) {
        if (issue instanceof GHIssue) {
            GHIssue i = (GHIssue) issue;
            return i.getMilestone();
        } else if (issue instanceof Issue) {
            Issue i = (Issue) issue;
            return i.getMilestone();
        }
        return null;
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE) {
            return new Class[]{Boolean.class};
        }
        return null;
    }

    @Override
    public void change( Event e, Object[] delta, Changer.ChangeMode mode) {
        for (Object issue : getExpr().getArray(e)) {
            switch (mode) {
                case SET:
                    if (issue instanceof Issue) {
                        Issue i = (Issue) issue;
                        i.setMilestone((GHMilestone) delta[0]);
                    }
                    break;
                case DELETE:
                    if (issue instanceof Issue) {
                        Issue i = (Issue) issue;
                        i.setMilestone(null);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "milestone";
    }

    @Override
    public Class<? extends GHMilestone> getReturnType() {
        return GHMilestone.class;
    }
}
