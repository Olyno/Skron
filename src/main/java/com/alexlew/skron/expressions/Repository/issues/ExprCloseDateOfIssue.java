package com.alexlew.skron.expressions.Repository.issues;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;

import java.util.Date;

@Name("Close date of issue")
@Description("Returns the date when the issue has been closed")
@Examples({
        "set {_closedate} to close date of {_issue}"
})
@Since("1.0")

public class ExprCloseDateOfIssue extends SimplePropertyExpression<GHIssue, Date> {

    static {
        register(ExprCloseDateOfIssue.class, Date.class,
                "[the] [skron] close[d] date", "issue");
    }

    @Override
    public Date convert(GHIssue issue) {
        return issue.getClosedAt();
    }

    @Override
    protected String getPropertyName() {
        return "close date";
    }

    @Override
    public Class<? extends Date> getReturnType() {
        return Date.class;
    }
}
