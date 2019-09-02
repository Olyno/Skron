package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

@Name("Parent of Repository")
@Description("Returns the parent of a repository. The repository must be a fork.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprParentOfRepository extends SimplePropertyExpression<GHRepository, GHRepository> {

    static {
        register(ExprParentOfRepository.class, GHRepository.class,
                "parent", "repository"
        );
    }

    @Override
    public GHRepository convert(GHRepository repository) {
        try {
            return repository.getParent();
        } catch (IOException ex) {
            Skript.exception(ex);
        }
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "parent";
    }

    @Override
    public Class<? extends GHRepository> getReturnType() {
        return GHRepository.class;
    }
}
