package com.alexlew.skron.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alexlew.skron.effects.EffLogin;
import org.bukkit.event.Event;
import org.kohsuke.github.GHUser;

import java.io.IOException;

@Name("Github user")
@Description("Return the github user from the name of a user.")
@Examples({
        "set {_githubuser} to github user \"AlexLew95\""
})
@Since("1.0")

public class ExprGithubUser extends SimpleExpression<GHUser> {

    static {
        Skript.registerExpression(ExprGithubUser.class, GHUser.class, ExpressionType.SIMPLE,
                "[skron] github user %string%"
        );
    }

    private Expression<String> username;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        username = (Expression<String>) expr[0];
        return true;
    }

    @Override
    protected GHUser[] get( Event e ) {
        try {
            return new GHUser[] {EffLogin.account.getUser(username.getSingle(e))};
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends GHUser> getReturnType() {
        return GHUser.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "github user " + username.getSingle(e);
    }

}
