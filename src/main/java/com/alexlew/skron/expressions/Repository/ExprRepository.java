package com.alexlew.skron.expressions.Repository;


import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alexlew.skron.Skron;
import com.alexlew.skron.effects.EffCreateRepository;
import com.alexlew.skron.effects.EffLogin;
import org.bukkit.event.Event;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

@Name("Repository expression")
@Description("Return a repository if you precise the name of the repository.")
@Examples({
        "set {_repository} to repository \"Test\""
})
@Since("1.0")

public class ExprRepository extends SimpleExpression<GHRepository> {

    static {
        Skript.registerExpression(ExprRepository.class, GHRepository.class, ExpressionType.SIMPLE,
                "[the] [skron] repo[sitory] [(with name|named)] %string%");
    }

    private Expression<String> repository;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        repository = (Expression<String>) expr[0];
        return true;
    }

    @Override
    protected GHRepository[] get( Event e ) {
        GHRepository repo = null;
        try {
            repo = repository.getSingle(e).contains("/") ?
                    EffLogin.account.getRepository(repository.getSingle(e)) :
                    EffLogin.account.getMyself().getRepository(repository.getSingle(e));
            if (repo != null) {
                EffCreateRepository.lastRepository = repo;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (NullPointerException e1) {
            Skron.error("You must to be login to your account before use it.");
        }
        return new GHRepository[] {repo};
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
        return "the repository " + repository.getSingle(e);
    }

}
