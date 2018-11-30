package com.alexlew.skron.expressions.Repository.commits;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHCommit;

@Name("SHA1 of commit")
@Description("Returns the SHA1 of a commit")
@Examples({
        "broadcast sha1 of {_commit}"
})
@Since("1.0")

public class ExprSHA1OfCommit extends SimplePropertyExpression<GHCommit, String> {

    static {
        register(ExprSHA1OfCommit.class, String.class,
                "[the] [skron] sha[1]", "commit");
    }

    @Override
    public String convert( GHCommit commit) {
        return commit.getSHA1();
    }

    @Override
    protected String getPropertyName() {
        return "sha1";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
