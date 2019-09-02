package com.olyno.skron.skript.expressions.repository;

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
import com.olyno.skron.Skron;
import org.bukkit.event.Event;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

@Name("Branch of Repository")
@Description("Returns a specific branch of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprBranchOfRepository extends SimpleExpression<GHBranch> {

    static {
        Skript.registerExpression(ExprBranchOfRepository.class, GHBranch.class, ExpressionType.SIMPLE,
                "[the] branch %string% of %repository%",
                "[the] %repository%'s branch %string%"
        );
    }

    private Expression<String> name;
    private Expression<GHRepository> repository;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (matchedPattern == 1) {
            name = (Expression<String>) expr[0];
            repository = (Expression<GHRepository>) expr[1];
        } else {
            name = (Expression<String>) expr[1];
            repository = (Expression<GHRepository>) expr[0];
        }
        return true;
    }

    @Override
    protected GHBranch[] get(Event e) {
        GHRepository repo = repository.getSingle(e);
        String branchName = name.getSingle(e);
        try {
            return new GHBranch[]{repo.getBranch(branchName)};
        } catch (IOException ex) {
            Skron.error("This branch doesn't exist on the repository \"" + repo.getName() + "\": " + branchName);
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends GHBranch> getReturnType() {
        return GHBranch.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "branch " + name.toString(e, debug) + " of repository " + repository.toString(e, debug);
    }
}
