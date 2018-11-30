package com.alexlew.skron.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommitComment;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.PagedIterable;

import java.util.List;

@Name("Comments count of issue/commit")
@Description("Returns the number of comments of an issue/commit")
@Examples({
        "set {_comments::*} to comments count of {_issue}"
})
@Since("1.0")


public class ExprCommentsCountOfSomething extends SimplePropertyExpression<Object, Integer> {

    static {
        register(ExprCommentsCountOfSomething.class, Integer.class,
                "[the] [skron] comments count", "object");
    }

    @Override
    public Integer convert( Object o ) {
        if (o instanceof GHIssue) {
            GHIssue issue = (GHIssue) o;
            return issue.getCommentsCount();
        } else if (o instanceof GHCommit) {
            GHCommit commit = (GHCommit) o;
            PagedIterable<GHCommitComment> comments = commit.listComments();
            List<GHCommitComment> n = comments.asList();
            return n.size();
        }
        return 0;

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

