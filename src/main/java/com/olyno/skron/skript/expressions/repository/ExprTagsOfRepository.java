package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHTag;

import java.io.IOException;

@Name("Tags of Repository")
@Description("Returns tags of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprTagsOfRepository extends MultiplePropertyExpression<GHRepository, GHTag> {

    static {
        register(ExprTagsOfRepository.class, GHTag.class,
                "tags", "repository"
        );
    }

    @Override
    protected GHTag[] convert(GHRepository repository) {
        try {
            return repository.listTags().asList().toArray(new GHTag[0]);
        } catch (IOException ex) {
            Skript.exception(ex);
        }
        return null;
    }

    @Override
    public Class<? extends GHTag> getReturnType() {
        return GHTag.class;
    }

    @Override
    protected String getPropertyName() {
        return "tags";
    }

}
