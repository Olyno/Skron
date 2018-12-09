package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHRepository;

@Name("Forks count of repository")
@Description("Returns the count of forks from a repository")
@Examples({
        "broadcast \"There are %fork count of repository with name \"\"Test\"\"% forks of the repository \"Test\"\""
})
@Since("1.0")

public class ExprForksCountOfRepository extends SimplePropertyExpression<GHRepository, Integer> {

    static {
        register(ExprForksCountOfRepository.class, Integer.class,
                "[the] [skron] fork[s] count", "repository");
    }

    @Override
    public Integer convert( GHRepository repo) {
        return repo.getForks();
    }

    @Override
    protected String getPropertyName() {
        return "forks count";
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
}
