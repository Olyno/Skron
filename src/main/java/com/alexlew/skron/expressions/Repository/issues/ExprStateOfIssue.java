package com.alexlew.skron.expressions.Repository.issues;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;

import java.util.Date;

@Name("State of issue")
@Description("Returns the state of a issue")
@Examples({
        "set {_state} to state of {_issue}"
})
@Since("1.0")

public class ExprStateOfIssue extends SimplePropertyExpression<GHIssue, GHIssueState> {

    static {
        register(ExprStateOfIssue.class, GHIssueState.class,
                "[the] [skron] state", "issue");
    }

    @Override
    public GHIssueState convert(GHIssue issue) {
        return issue.getState();
    }

    @Override
    protected String getPropertyName() {
        return "state";
    }

    @Override
    public Class<? extends GHIssueState> getReturnType() {
        return GHIssueState.class;
    }
}
