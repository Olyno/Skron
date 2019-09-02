package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;

@Name("Watchers of Repository")
@Description("Returns watchers of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprWatchersOfRepository extends MultiplePropertyExpression<GHRepository, GHUser> {

    static {
        register(ExprWatchersOfRepository.class, GHUser.class,
                "watchers", "repository"
        );
    }

    @Override
    protected GHUser[] convert(GHRepository repository) {
        return repository.listSubscribers().asList().toArray(new GHUser[0]);
    }

    @Override
    public Class<? extends GHUser> getReturnType() {
        return GHUser.class;
    }

    @Override
    protected String getPropertyName() {
        return "watchers";
    }

}
