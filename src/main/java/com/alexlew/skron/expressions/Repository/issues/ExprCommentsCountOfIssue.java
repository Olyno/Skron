package com.alexlew.skron.expressions.Repository.issues;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHIssue;

@Name("Comments count of issue")
@Description("Returns the number of comments of an issue")
@Examples({
        "set {_comments::*} to comments count of {_issue}"
})
@Since("1.0")


public class ExprCommentsCountOfIssue extends SimplePropertyExpression<GHIssue, Integer> {

    static {
        register(ExprCommentsCountOfIssue.class, Integer.class,
                "[the] [skron] comments count",
                "issue");
    }

    @Override
    public Integer convert( GHIssue issue ) {
        return issue.getCommentsCount();
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    protected String getPropertyName() {
        return "comments count";
    }


}

