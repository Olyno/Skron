package com.olyno.skron.skript.expressions.repository.issues;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;

@Name("Issues of Repository")
@Description("Returns all issues of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprIssuesOfRepository extends MultiplePropertyExpression<GHRepository, GHIssue> {

    static {
        register(ExprIssuesOfRepository.class, GHIssue.class,
                "issues", "repository"
        );
    }

    @Override
    protected GHIssue[] convert(GHRepository repository) {
        return repository.listIssues(GHIssueState.ALL).asList().toArray(new GHIssue[0]);
    }

    @Override
    public Class<? extends GHIssue> getReturnType() {
        return GHIssue.class;
    }

    @Override
    protected String getPropertyName() {
        return "issues";
    }

}
