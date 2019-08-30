package com.olyno.skron.skript.expressions;

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
import com.olyno.skron.skript.effects.EffLogin;
import org.bukkit.event.Event;
import org.kohsuke.github.GitHub;

@Name("Github Account")
@Description("Returns a connected github account from its name.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprGithubAccount extends SimpleExpression<GitHub> {

    static {
        Skript.registerExpression(ExprGithubAccount.class, GitHub.class, ExpressionType.SIMPLE,
                "github account %string%"
        );
    }

    private Expression<String> accountName;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        accountName = (Expression<String>) expr[0];
        return true;
    }

    @Override
    protected GitHub[] get(Event e) {
        return new GitHub[]{EffLogin.accounts.get(accountName.getSingle(e))};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends GitHub> getReturnType() {
        return GitHub.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "github account " + accountName.toString(e, debug);
    }
}
