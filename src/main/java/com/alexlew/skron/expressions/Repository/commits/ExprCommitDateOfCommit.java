package com.alexlew.skron.expressions.Repository.commits;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHCommit;

import java.io.IOException;
import java.util.Date;

@Name("Commit date of commit")
@Description("Returns the commit date of a commit")
@Examples({
        "set {_date} to commit date of commit with sha \"d92702b0e9103ff5edb74e9137364fa01daffeea\"",
        "broadcast \"%{_date}%\""
})
@Since("1.0")

public class ExprCommitDateOfCommit extends SimplePropertyExpression<GHCommit, Date> {

    static {
        register(ExprCommitDateOfCommit.class, Date.class,
                "[the] [skron] commit date", "commit");
    }

    @Override
    public Date convert( GHCommit commit) {
        try {
            return commit.getCommitDate();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "commit date";
    }

    @Override
    public Class<? extends Date> getReturnType() {
        return Date.class;
    }
}
