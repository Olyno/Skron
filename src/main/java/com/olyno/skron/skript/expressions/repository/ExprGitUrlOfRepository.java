package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHRepository;

@Name("Git URL of Repository")
@Description("Returns the git url of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprGitUrlOfRepository extends SimplePropertyExpression<GHRepository, String> {

    static {
        register(ExprGitUrlOfRepository.class, String.class,
                "git [transport] url", "repository"
        );
    }

    @Override
    public String convert(GHRepository repository) {
        return repository.getGitTransportUrl();
    }

    @Override
    protected String getPropertyName() {
        return "git url";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
