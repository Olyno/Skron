package com.alexlew.skron.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.*;

import java.io.IOException;

@Name("Author of Issue/Commit")
@Description("Returns the author of an issue/commit")
@Examples({
        "set {_author} to author of {_issue}"
})
@Since("1.0")

public class ExprAuthorOfSomething extends SimplePropertyExpression<Object, GHUser> {

    static {
        register(ExprAuthorOfSomething.class, GHUser.class,
                "[the] [skron] author", "object");
    }

    @Override
    public GHUser convert(Object o) {
        try {
            if (o instanceof GHIssue) {
                GHIssue issue = (GHIssue) o;
                return issue.getUser();
            } else if (o instanceof GHCommit) {
                GHCommit commit = (GHCommit) o;
                return commit.getAuthor();
            } else if (o instanceof GHCommitComment) {
                GHCommitComment commit = (GHCommitComment) o;
                return commit.getUser();
            } else if (o instanceof GHIssueComment) {
                GHIssueComment commit = (GHIssueComment) o;
                return commit.getUser();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "author";
    }

    @Override
    public Class<? extends GHUser> getReturnType() {
        return GHUser.class;
    }
}
