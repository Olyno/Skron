package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

@Name("Branches of Repository")
@Description("Returns all branches of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprBranchesOfRepository extends MultiplePropertyExpression<GHRepository, GHBranch> {

    static {
        register(ExprBranchesOfRepository.class, GHBranch.class,
                "branches", "repository"
        );
    }

    @Override
    protected GHBranch[] convert(GHRepository repository) {
        try {
            return repository.getBranches().values().toArray(new GHBranch[0]);
        } catch (IOException ex) {
            Skript.exception(ex, "Something wrong with this repository: " + repository.getName());
        }
        return null;
    }

    @Override
    public Class<? extends GHBranch> getReturnType() {
        return GHBranch.class;
    }

    @Override
    protected String getPropertyName() {
        return "branches";
    }

}
