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
import org.kohsuke.github.GHGist;

import java.io.IOException;

@Name("Gist by id")
@Description("Return the gist from a id.")
@Examples({
        "set {_gist} to gist by id \"7937f6441c259087175765ad31da82e2\""
})
@Since("1.0")

public class ExprGistById extends SimpleExpression<GHGist> {

    static {
        Skript.registerExpression(ExprGistById.class, GHGist.class, ExpressionType.SIMPLE,
                "[skron] gist [(by|with) id] %string%"
        );
    }

    private Expression<String> id;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        id = (Expression<String>) expr[0];
        return true;
    }

    @Override
    protected GHGist[] get( Event e ) {
        try {
            return new GHGist[] {EffLogin.account.getGist(id.getSingle(e))};
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
    public Class<? extends GHGist> getReturnType() {
        return GHGist.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "gist by id " + id.getSingle(e);
    }

}
