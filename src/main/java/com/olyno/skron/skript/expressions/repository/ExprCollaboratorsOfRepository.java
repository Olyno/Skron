package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;

import java.io.IOException;

@Name("Collaborators of Repository")
@Description("Returns all collaborators of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprCollaboratorsOfRepository extends MultiplePropertyExpression<GHRepository, GHUser> {

    static {
        register(ExprCollaboratorsOfRepository.class, GHUser.class,
                "collaborators", "repository"
        );
    }

    @Override
    protected GHUser[] convert(GHRepository repository) {
        try {
            return repository.getCollaborators().toArray(new GHUser[0]);
        } catch (IOException ex) {
            Skript.exception(ex, "Something wrong with this repository: " + repository.getName());
        }
        return null;
    }

    @Override
    public Class<? extends GHUser> getReturnType() {
        return GHUser.class;
    }

    @Override
    protected String getPropertyName() {
        return "collaborators";
    }

}
