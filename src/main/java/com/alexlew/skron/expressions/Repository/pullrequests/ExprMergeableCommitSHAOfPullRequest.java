package com.alexlew.skron.expressions.Repository.pullrequests;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHPullRequest;

import java.io.IOException;

@Name("Merge commit SHA of pull request")
@Description("Returns the merge commit SHA of a pull request")
@Examples({
        "broadcast merge commit sha of {_pullrequest}"
})
@Since("1.0")

public class ExprMergeableCommitSHAOfPullRequest extends SimplePropertyExpression<GHPullRequest, String> {

    static {
        register(ExprMergeableCommitSHAOfPullRequest.class, String.class,
                "[the] [skron] merge [commit] (SHA|sha)", "pullrequest");
    }

    @Override
    public String convert( GHPullRequest pr) {
        try {
            return pr.getMergeCommitSha();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "merge commit sha";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
