package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;

@Name("Stargazers of Repository")
@Description("Returns Stargazers of a repository. It's who gave a star to a repository")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprStargazersOfRepository extends MultiplePropertyExpression<GHRepository, GHUser> {

    static {
        register(ExprStargazersOfRepository.class, GHUser.class,
                "stargazers", "repository"
        );
    }

    @Override
    protected GHUser[] convert(GHRepository repository) {
        return repository.listStargazers().asList().toArray(new GHUser[0]);
    }

    @Override
    public Class<? extends GHUser> getReturnType() {
        return GHUser.class;
    }

    @Override
    protected String getPropertyName() {
        return "stargazers";
    }

}
