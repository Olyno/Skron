package com.alexlew.skron.expressions.issues;

import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;

@Name("Repository of issue")
@Description("Returns the repository of a issue")
@Examples({
        "set {_repository} to repository of {_issue}"
})
@Since("1.0")

public class ExprRepositoryOfIssue extends SimplePropertyExpression<GHIssue, GHRepository> {

    static {
        register(ExprRepositoryOfIssue.class, GHRepository.class,
                "[the] repository", "issue");
    }

    @Override
    public GHRepository convert(GHIssue issue) {
        return issue.getRepository();
    }

    @Override
    protected String getPropertyName() {
        return "repository";
    }

    @Override
    public Class<? extends GHRepository> getReturnType() {
        return GHRepository.class;
    }
}
