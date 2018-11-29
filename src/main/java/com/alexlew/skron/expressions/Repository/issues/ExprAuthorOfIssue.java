package com.alexlew.skron.expressions.Repository.issues;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHUser;

import java.io.IOException;

@Name("Author of Issue")
@Description("Returns the author of an issue")
@Examples({
        "set {_author} to author of {_issue}"
})
@Since("1.0")

public class ExprAuthorOfIssue extends SimplePropertyExpression<GHIssue, GHUser> {

    static {
        register(ExprAuthorOfIssue.class, GHUser.class,
                "[the] [skron] author", "issue");
    }

    @Override
    public GHUser convert(GHIssue issue) {
        try {
            return issue.getUser();
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
