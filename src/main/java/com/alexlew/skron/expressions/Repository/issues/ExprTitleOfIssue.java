package com.alexlew.skron.expressions.Repository.issues;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Issue;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;

import java.io.IOException;

@Name("Title of Issue")
@Description("Returns the title of an issue. Can be set in a issue builder scope.")
@Examples({
        "# outside a issue builder scope",
        "set {_title} to title of {_issue}",
        "# inside a issue builder scope",
        "make new issue:",
        "\tset title of issue builder to \"New issue!\""
})
@Since("1.0")

public class ExprTitleOfIssue extends SimplePropertyExpression<Object, String> {

    static {
        register(ExprTitleOfIssue.class, String.class,
                "[the] [skron] title", "object");
    }

    @Override
    public String convert(Object issue) {
        if (issue instanceof GHIssue) {
            GHIssue i = (GHIssue) issue;
            return i.getTitle();
        } else if (issue instanceof Issue) {
            Issue i = (Issue) issue;
            return i.getTitle();
        }
        return null;

    }

    @Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.DELETE) {
            return new Class[]{GHIssue.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, ChangeMode mode) {
        for (Object issue : getExpr().getArray(e)) {
            switch (mode) {
                case SET:
                    try {
                        if (issue instanceof GHIssue) {
                            GHIssue i = (GHIssue) issue;
                            i.setTitle((String) delta[0]);
                        } else if (issue instanceof Issue) {
                            Issue i = (Issue) issue;
                            i.setTitle((String) delta[0]);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case DELETE:
                    if (issue instanceof Issue) {
                        Issue i = (Issue) issue;
                        i.setTitle(null);
                    }
                default:
                    break;
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "title";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
