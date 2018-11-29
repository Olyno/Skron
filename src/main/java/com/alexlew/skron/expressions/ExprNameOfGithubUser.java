package com.alexlew.skron.expressions;

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

@Name("Name of github user")
@Description("Returns the name of a github user")
@Examples({
        "set {_name} to name of {_user}"
})
@Since("1.0")

public class ExprNameOfGithubUser extends SimplePropertyExpression<GHUser, String> {

    static {
        register(ExprNameOfGithubUser.class, String.class,
                "[the] [skron] name", "githubuser");
    }

    @Override
    public String convert(GHUser user) {
        try {
            return user.getName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "name";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
