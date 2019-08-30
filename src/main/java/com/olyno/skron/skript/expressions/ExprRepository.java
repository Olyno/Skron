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
import com.olyno.skron.skript.scopes.ScopeRepositoryBuilderCreation;
import com.olyno.skron.util.EffectSection;
import com.olyno.skron.util.classes.RepositoryBuilder;
import org.bukkit.event.Event;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;

@Name("Repository")
@Description("Returns a repository from its name. Can be an existing repository or a repository builder.\n"
        + "If name is precised, it will be an existing repository, else a repository builder."
)
@Examples({
        ""
})
@Since("1.0.0")

public class ExprRepository extends SimpleExpression<Object> {

    static {
        Skript.registerExpression(ExprRepository.class, Object.class, ExpressionType.SIMPLE,
                "[the] repo[sitory] [(with name|named) %-string%] [(from|of) %-githubaccount%]"
        );
    }

    private Expression<String> repositoryName;
    private Expression<GitHub> account;
    private GitHub user;
    private Boolean scope = false;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        repositoryName = (Expression<String>) expr[0];
        account = (Expression<GitHub>) expr[1];
        scope = EffectSection.isCurrentSection(ScopeRepositoryBuilderCreation.class);
        return true;
    }

    @Override
    protected Object[] get(Event e) {
        String name = repositoryName != null ? repositoryName.getSingle(e) : null;
        user = account != null ? account.getSingle(e) : EffLogin.accounts.values().iterator().next();
        if (!scope) {
            if (name != null) {
                try {
                    return new GHRepository[]{user.getMyself().getRepository(name)};
                } catch (IOException ex) {
                    Skript.exception(ex);
                }
            }
        } else {
            return new RepositoryBuilder[]{
                    scope ? ScopeRepositoryBuilderCreation.lastRepositoryBuilder
                            : name != null ? ScopeRepositoryBuilderCreation.repositoriesBuilder.get(name)
                            : null
            };
        }

        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Object> getReturnType() {
        return Object.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        try {
            return repositoryName != null ? "the repository " + repositoryName.toString(e, debug) + " from github account " + (account != null ? account.toString(e, debug) : user.getMyself().getName()) : "this repository builder";
        } catch (IOException | NullPointerException ex) {
            return repositoryName != null ? "the repository " + repositoryName.toString(e, debug) + " from unknown github account" : "this repository builder";
        }
    }

}
