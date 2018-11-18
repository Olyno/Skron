package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alexlew.skron.effects.EffCreateRepository;
import org.bukkit.event.Event;
import org.kohsuke.github.GHRepository;

@Name("Last repository")
@Description("Returns the last repository")
@Examples({
        "set {_issues::*} to issues of last repository"
})
@Since("1.0")

public class ExprLastRepository extends SimpleExpression<GHRepository> {

    static {
        Skript.registerExpression(ExprLastRepository.class, GHRepository.class,
                ExpressionType.SIMPLE, "[the] last[ly] repo[sitory]");
    }

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        return true;
    }

    @Override
    protected GHRepository[] get( Event e ) {
        return new GHRepository[] {EffCreateRepository.lastRepository};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends GHRepository> getReturnType() {
        return GHRepository.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "last repository";
    }
}
