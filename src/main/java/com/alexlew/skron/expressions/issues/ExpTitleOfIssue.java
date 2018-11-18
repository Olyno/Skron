package com.alexlew.skron.expressions.issues;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.Skron;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;

import java.io.IOException;

@Name("Title of Issue")
@Description("Returns the title of an issue")
@Examples({
        "set {_body} to title of {_issue}"
})
@Since("1.0")

public class ExpTitleOfIssue extends SimplePropertyExpression<GHIssue, String> {

    static {
        register(ExpTitleOfIssue.class, String.class,
                "[the] title", "issue");
    }

    @Override
    public String convert(GHIssue issue) {
        return issue.getTitle();
    }

    @Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET) {
            return new Class[]{GHIssue.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, ChangeMode mode) {
        for (GHIssue issue : getExpr().getArray(e)) {
            switch (mode) {
                case SET:
                    try {
                        issue.setTitle((String) delta[0]);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case DELETE:
                    try {
                        issue.setTitle(null);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
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
