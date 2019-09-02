package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

@Name("Releases of Repository")
@Description("Returns all releases of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprReleasesOfRepository extends MultiplePropertyExpression<GHRepository, GHRelease> {

    static {
        register(ExprReleasesOfRepository.class, GHRelease.class,
                "releases", "repository"
        );
    }

    @Override
    protected GHRelease[] convert(GHRepository repository) {
        try {
            return repository.listReleases().asList().toArray(new GHRelease[0]);
        } catch (IOException ex) {
            Skript.exception(ex);
        }
        return null;
    }

    @Override
    public Class<? extends GHRelease> getReturnType() {
        return GHRelease.class;
    }

    @Override
    protected String getPropertyName() {
        return "releases";
    }

}
