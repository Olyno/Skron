package com.alexlew.skron.expressions.Repository.commits;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHCommit;

import java.io.IOException;

@Name("Lines changed of commit")
@Description("Returns the number of lines changed of a commit")
@Examples({
        "set {_number} to lines changed of commit with sha \"d92702b0e9103ff5edb74e9137364fa01daffeea\"",
        "broadcast \"There are %{_number}% lines changed!\""
})
@Since("1.0")

public class ExprLinesChangedOfCommit extends SimplePropertyExpression<GHCommit, Integer> {

    static {
        register(ExprLinesChangedOfCommit.class, Integer.class,
                "[the] [skron] lines changed", "commit");
    }

    @Override
    public Integer convert( GHCommit commit) {
        try {
            return commit.getLinesChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "lines changed";
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
}
