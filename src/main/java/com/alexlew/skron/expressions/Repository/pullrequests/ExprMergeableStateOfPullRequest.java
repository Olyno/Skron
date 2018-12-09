package com.alexlew.skron.expressions.Repository.pullrequests;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHPullRequest;

import java.io.IOException;

@Name("Mergeable state of pull request")
@Description("Returns the mergeable state of a pull request")
@Examples({
        "broadcast mergeable state of {_pullrequest}"
})
@Since("1.0")

public class ExprMergeableStateOfPullRequest extends SimplePropertyExpression<GHPullRequest, String> {

    static {
        register(ExprMergeableStateOfPullRequest.class, String.class,
                "[the] [skron] mergeable state", "pullrequest");
    }

    @Override
    public String convert( GHPullRequest pr) {
        try {
            return pr.getMergeableState();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "mergeable state";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
