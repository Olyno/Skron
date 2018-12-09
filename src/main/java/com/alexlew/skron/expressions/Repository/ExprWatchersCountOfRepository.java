package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHRepository;

@Name("Watchers count of repository")
@Description("Returns the count of watchers from a repository")
@Examples({
        "broadcast \"There are %watchers count of repository with name \"\"Test\"\"% forks of the repository \"Test\"\""
})
@Since("1.0")

public class ExprWatchersCountOfRepository extends SimplePropertyExpression<GHRepository, Integer> {

    static {
        register(ExprWatchersCountOfRepository.class, Integer.class,
                "[the] [skron] watcher[s] count", "repository");
    }

    @Override
    public Integer convert( GHRepository repo) {
        return repo.getWatchers();
    }

    @Override
    protected String getPropertyName() {
        return "watchers count";
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
}
