package com.alexlew.skron.expressions.Repository.commits;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHCommit;

import java.io.IOException;

@Name("HTML url of commit")
@Description("Returns the html url of a commit")
@Examples({
        "set {_url} to html url of commit with sha \"d92702b0e9103ff5edb74e9137364fa01daffeea\"",
        "broadcast \"Html url: %{_url}%\""
})
@Since("1.0")

public class ExprHTMLUrlOfCommit extends SimplePropertyExpression<GHCommit, String> {

    static {
        register(ExprHTMLUrlOfCommit.class, String.class,
                "[the] [skron] html url", "commit");
    }

    @Override
    public String convert( GHCommit commit) {
        return commit.getHtmlUrl().toString();
    }

    @Override
    protected String getPropertyName() {
        return "html url";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
