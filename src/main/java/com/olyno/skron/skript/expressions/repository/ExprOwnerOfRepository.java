package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;

import java.io.IOException;

@Name("Owner of Repository")
@Description("Returns owner of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprOwnerOfRepository extends SimplePropertyExpression<GHRepository, GHUser> {

    static {
        register(ExprOwnerOfRepository.class, GHUser.class,
                "[the] repo[sitory] owner", "repository"
        );
    }

    @Override
    public GHUser convert(GHRepository repository) {
        try {
            return repository.getOwner();
        } catch (IOException ex) {
            Skript.exception(ex);
        }
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "repository owner";
    }

    @Override
    public Class<? extends GHUser> getReturnType() {
        return GHUser.class;
    }
}
