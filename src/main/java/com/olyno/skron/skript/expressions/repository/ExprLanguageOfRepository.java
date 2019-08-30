package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHRepository;

@Name("Language of Repository")
@Description("Returns language of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprLanguageOfRepository extends SimplePropertyExpression<GHRepository, String> {

    static {
        register(ExprLanguageOfRepository.class, String.class,
                "[the] language", "repository"
        );
    }

    @Override
    public String convert(GHRepository repository) {
        return repository.getLanguage();
    }

    @Override
    protected String getPropertyName() {
        return "language";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
