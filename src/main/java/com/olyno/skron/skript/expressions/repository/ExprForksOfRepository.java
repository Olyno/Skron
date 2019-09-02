package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHRepository;

@Name("Forks of Repository")
@Description("Returns all forks of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprForksOfRepository extends MultiplePropertyExpression<GHRepository, GHRepository> {

    static {
        register(ExprForksOfRepository.class, GHRepository.class,
                "forks", "repository"
        );
    }

    @Override
    protected GHRepository[] convert(GHRepository repository) {
        return repository.listForks().asList().toArray(new GHRepository[0]);
    }

    @Override
    public Class<? extends GHRepository> getReturnType() {
        return GHRepository.class;
    }

    @Override
    protected String getPropertyName() {
        return "forks";
    }

}
