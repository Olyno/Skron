package com.alexlew.skron.expressions.Repository.comments;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommitComment;

import java.io.IOException;

@Name("Issue of comment")
@Description("Returns the issue of a comment")
@Examples({
        "set {_issue} to issue of {_comment}"
})
@Since("1.0")

public class ExprCommitOfComment extends SimplePropertyExpression<GHCommitComment, GHCommit> {

    static {
        register(ExprCommitOfComment.class, GHCommit.class,
                "[the] [skron] issue", "commitcomment");
    }

    @Override
    public GHCommit convert( GHCommitComment comment) {
        try {
            return comment.getCommit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "issue";
    }

    @Override
    public Class<? extends GHCommit> getReturnType() {
        return GHCommit.class;
    }
}
