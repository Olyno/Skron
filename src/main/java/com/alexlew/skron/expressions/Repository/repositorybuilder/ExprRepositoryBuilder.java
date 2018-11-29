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
import com.alexlew.skron.util.EffectSection;
import org.bukkit.event.Event;

@Name("Repository builder expression")
@Description("If it isn't inside an repository creation scope, this expression returns a new repository builder. " +
        "If it is inside of an repository creation scope, it returns the repository builder that belongs to that scope.")
@Examples({
        "# outside a scope",
        "",
        "set {_e} to a new repository builder",
        "",
        "# or in a scope",
        "",
        "make a new repository:",
        "\tset the name of the repository builder to \"My Repository\"",
        "\tset the description of the repository builder to \"receiver@gmail.com\"",
        "\tset the home page of the repository builder to \"Put a url here\"",
        "\tset the issue tracker state of the repository builder to true",
        "\tset the downloadable state of the repository builder to true",
        "\tset the wiki state of the repository builder to false",
        "\tset the private state of the repository builder to false ",
        "\tset the auto init state of the repository builder to true",
        "set {_repository} to last repository builder"
})
@Since("1.0")

public class ExprRepositoryBuilder extends SimpleExpression<Repository> {

    static {
        Skript.registerExpression(ExprRepositoryBuilder.class, Repository.class, ExpressionType.SIMPLE,
                "[(the|an|[a] new)] [skron] repo[sitory] build[er]");
    }

    private Boolean scope = false;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
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
        return "the repository builder";
    }

}
