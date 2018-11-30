package com.alexlew.skron.expressions.Repository.commits;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHUser;

import java.io.IOException;

@Name("Committer of commit")
@Description("Returns the committer of a commit")
@Examples({
        "broadcast committer of commit with sha \"d92702b0e9103ff5edb74e9137364fa01daffeea\""
})
@Since("1.0")

public class ExprCommitterOfCommit extends SimplePropertyExpression<GHCommit, GHUser> {

    static {
        register(ExprCommitterOfCommit.class, GHUser.class,
                "[the] [skron] committer", "commit");
    }

    @Override
    public GHUser convert( GHCommit commit) {
        try {
            return commit.getCommitter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "committer";
    }

    @Override
    public Class<? extends GHUser> getReturnType() {
        return GHUser.class;
    }
}
