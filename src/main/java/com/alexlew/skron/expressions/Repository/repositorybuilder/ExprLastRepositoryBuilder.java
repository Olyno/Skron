package com.alexlew.skron.expressions.Repository.repositorybuilder;

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
import com.alexlew.skron.scopes.ScopeNewRepo;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;

@Name("Last repository builder")
@Description("Returns the last repository builder. It's set in a repository scope")
@Examples({
        "create last repository builder"
})
@Since("1.0")

public class ExprLastRepositoryBuilder extends SimpleExpression<Repository> {

    static {
        Skript.registerExpression(ExprLastRepositoryBuilder.class, Repository.class,
                ExpressionType.SIMPLE, "[the] [skron] last[ly] repo[sitory] build[er]");
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
        return "last repository builder";
    }
}
