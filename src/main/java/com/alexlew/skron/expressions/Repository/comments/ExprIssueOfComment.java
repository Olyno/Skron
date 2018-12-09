package com.alexlew.skron.expressions.Repository.comments;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;

@Name("Issue of comment")
@Description("Returns the issue of a comment")
@Examples({
        "set {_issue} to issue of {_comment}"
})
@Since("1.0")

public class ExprIssueOfComment extends SimplePropertyExpression<GHIssueComment, GHIssue> {

    static {
        register(ExprIssueOfComment.class, GHIssue.class,
                "[the] [skron] issue", "issuecomment");
    }

    @Override
    public GHIssue convert( GHIssueComment comment) {
        return comment.getParent();
    }

    @Override
    protected String getPropertyName() {
        return "issue";
    }

    @Override
    public Class<? extends GHIssue> getReturnType() {
        return GHIssue.class;
    }
}
