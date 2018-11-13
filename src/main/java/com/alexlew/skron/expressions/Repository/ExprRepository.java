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
import com.alexlew.skron.util.EffectSection;
import org.bukkit.event.Event;

@Name("Repository expression")
@Description("If it isn't inside an Repository creation scope, this expression returns a new Repository. " +
        "If it is inside of an Repository creation scope, it returns the Repository that belongs to that scope.")
@Examples({
        "# outside a scope",
        "",
        "set {_e} to a new Repository",
        "",
        "# or in a scope",
        "",
        "make a new Repository:",
        "\tset the name of the Repository to \"My Repository\"",
        "\tset the description of the Repository to \"receiver@gmail.com\"",
        "\tset the private state of the Repository to false ",
        "\tset the auto init state of the Repository to true",
        "set {_email} to last Repository"
})
@Since("1.0")

public class ExprRepository extends SimpleExpression<Repository> {

    static {
        Skript.registerExpression(ExprRepository.class, Repository.class, ExpressionType.SIMPLE,
                "[(the|an|[a] new)] repo[sitory]");
    }

    private Boolean scope = false;

    @Override
    public boolean init( Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        scope = EffectSection.isCurrentSection(ScopeNewRepo.class);
        return scope;
    }

    @Override
    protected Repository[] get( Event e ) {
        return new Repository[]{
                scope ? ScopeNewRepo.lastRepository : new Repository()
        };
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
        return "the Repository";
    }

}
