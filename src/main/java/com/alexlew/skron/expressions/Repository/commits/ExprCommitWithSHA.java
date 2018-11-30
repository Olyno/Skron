package com.alexlew.skron.expressions.Repository.commits;

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
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

@Name("Commit with SHA")
@Description("Return a commit from its SHA")
@Examples({
        "set {_commit} to commit with SHA \"d92702b0e9103ff5edb74e9137364fa01daffeea\" from repository \"Test\""
})
@Since("1.0")

public class ExprCommitWithSHA extends SimpleExpression<GHCommit> {

    static {
        Skript.registerExpression(ExprCommitWithSHA.class, GHCommit.class, ExpressionType.SIMPLE,
                "[skron] commit [with [(SHA|sha)]] %string% from %repository%"
        );
    }

    private Expression<String> sha;
    private Expression<GHRepository> repository;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        sha = (Expression<String>) expr[0];
        repository = (Expression<GHRepository>) expr[1];
        return true;
    }

    @Override
    protected GHCommit[] get( Event e ) {
        try {
            return new GHCommit[] {repository.getSingle(e).getCommit(sha.getSingle(e))};
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
    public Class<? extends GHCommit> getReturnType() {
        return GHCommit.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "commit with sha " + sha.getSingle(e) + " from repository " + repository.getSingle(e).getName();
    }

}
