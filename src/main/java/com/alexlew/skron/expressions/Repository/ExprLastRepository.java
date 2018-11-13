package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alexlew.skron.scopes.ScopeNewRepo;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;

@Name("Last Repository")
@Description("Returns the last Repository. It's set in a Repository scope")
@Examples({
        "create last Repository"
})
@Since("1.0")

public class ExprLastRepository extends SimpleExpression<Repository> {

    static {
        Skript.registerExpression(ExprLastRepository.class, Repository.class,
                ExpressionType.SIMPLE, "[the] last[ly] repo[sitory]");
    }

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        return true;
    }

    @Override
    protected Repository[] get( Event e ) {
        return new Repository[] {ScopeNewRepo.lastRepository};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Repository> getReturnType() {
        return Repository.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "last Repository";
    }
}
