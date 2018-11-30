package com.alexlew.skron.expressions.Repository.commits;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHCommit;

import java.io.IOException;
import java.util.Date;

@Name("Authored date of commit")
@Description("Returns the authored date of a commit")
@Examples({
        "set {_date} to authored date of commit with sha \"d92702b0e9103ff5edb74e9137364fa01daffeea\"",
        "broadcast %{_date}%"
})
@Since("1.0")

public class ExprAuthoredDateOfCommit extends SimplePropertyExpression<GHCommit, Date> {

    static {
        register(ExprAuthoredDateOfCommit.class, Date.class,
                "[the] [skron] authored date", "commit");
    }

    @Override
    public Date convert( GHCommit commit) {
        try {
            return commit.getAuthoredDate();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "authored date";
    }

    @Override
    public Class<? extends Date> getReturnType() {
        return Date.class;
    }
}
