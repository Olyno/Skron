package com.olyno.skron.skript.expressions.repository.issues;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;

@Name("Closed issues of Repository")
@Description("Returns closed issues of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprClosedIssuesOfRepository extends MultiplePropertyExpression<GHRepository, GHIssue> {

    static {
        register(ExprClosedIssuesOfRepository.class, GHIssue.class,
                "closed issues", "repository"
        );
    }

    @Override
    protected GHIssue[] convert(GHRepository repository) {
        return repository.listIssues(GHIssueState.CLOSED).asList().toArray(new GHIssue[0]);
    }

    @Override
    public Class<? extends GHIssue> getReturnType() {
        return GHIssue.class;
    }

    @Override
    protected String getPropertyName() {
        return "closed issues";
    }

}
