package com.olyno.skron.skript.expressions.repository.repositorybuilder;

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
import com.olyno.skron.skript.scopes.ScopeRepositoryBuilderCreation;
import com.olyno.skron.util.EffectSection;
import com.olyno.skron.util.classes.RepositoryBuilder;
import org.bukkit.event.Event;

@Name("Repository Builder")
@Description("Returns a repository builder. Needn't the name of the repository builder if it's used in a repository creation scope.")
@Examples({
        "command create:\n" +
                "\ttrigger:\n" +
                "\t\tmake new repository named \"test\":\n" +
                "\t\t\tset description of repository to \"this a debug message\"\n" +
                "\t\tcreate repository \"test\""
})
@Since("1.0.0")

public class ExprRepositoryBuilder extends SimpleExpression<RepositoryBuilder> {

    static {
        Skript.registerExpression(ExprRepositoryBuilder.class, RepositoryBuilder.class, ExpressionType.SIMPLE,
                "[the] repo[sitory] [builder (with name|named) %-string%]"
        );
    }

    private Expression<String> repositoryName;
    private Boolean scope = false;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        repositoryName = (Expression<String>) expr[0];
        scope = EffectSection.isCurrentSection(ScopeRepositoryBuilderCreation.class);
        return true;
    }

    @Override
    protected RepositoryBuilder[] get(Event e) {
        String name = repositoryName != null ? repositoryName.getSingle(e) : null;
        return new RepositoryBuilder[]{
                scope ? ScopeRepositoryBuilderCreation.lastRepositoryBuilder
                        : name != null ? ScopeRepositoryBuilderCreation.repositoriesBuilder.get(name)
                        : null
        };
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends RepositoryBuilder> getReturnType() {
        return RepositoryBuilder.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "repository builder" + (repositoryName != null ? " " + repositoryName.toString(e, debug) : "");
    }

}
