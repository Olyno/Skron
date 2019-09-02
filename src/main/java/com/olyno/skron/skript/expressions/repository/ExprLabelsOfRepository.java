package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHLabel;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

@Name("Labels of Repository")
@Description("Returns all labels of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprLabelsOfRepository extends MultiplePropertyExpression<GHRepository, GHLabel> {

    static {
        register(ExprLabelsOfRepository.class, GHLabel.class,
                "labels", "repository"
        );
    }

    @Override
    protected GHLabel[] convert(GHRepository repository) {
        try {
            return repository.listLabels().asList().toArray(new GHLabel[0]);
        } catch (IOException ex) {
            Skript.exception(ex);
        }
        return null;
    }

    @Override
    public Class<? extends GHLabel> getReturnType() {
        return GHLabel.class;
    }

    @Override
    protected String getPropertyName() {
        return "labels";
    }

}
