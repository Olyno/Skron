package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

@Name("Languages of Repository")
@Description("Returns languages of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprLanguagesOfRepository extends MultiplePropertyExpression<GHRepository, String> {

    static {
        register(ExprLanguagesOfRepository.class, String.class,
                "languages", "repository"
        );
    }

    @Override
    protected String[] convert(GHRepository repository) {
        try {
            return repository.listLanguages().keySet().toArray(new String[0]);
        } catch (IOException ex) {
            Skript.exception(ex);
        }
        return null;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    protected String getPropertyName() {
        return "languages";
    }

}
