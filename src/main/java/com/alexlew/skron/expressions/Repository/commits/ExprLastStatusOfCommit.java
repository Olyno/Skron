package com.alexlew.skron.expressions.Repository.commits;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommitStatus;

import java.io.IOException;

@Name("last status of commit")
@Description("Returns the last status of a commit")
@Examples({
        "set {_status} to last status of commit with sha \"d92702b0e9103ff5edb74e9137364fa01daffeea\""
})
@Since("1.0")

public class ExprLastStatusOfCommit extends SimplePropertyExpression<GHCommit, GHCommitStatus> {

    static {
        register(ExprLastStatusOfCommit.class, GHCommitStatus.class,
                "[the] [skron] last status", "commit");
    }

    @Override
    public GHCommitStatus convert( GHCommit commit) {
        try {
            return commit.getLastStatus();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "last status";
    }

    @Override
    public Class<? extends GHCommitStatus> getReturnType() {
        return GHCommitStatus.class;
    }
}
