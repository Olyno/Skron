package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHRepository;

@Name("Http transport Url of repository")
@Description("Returns the http transport url of a repository, such as \"https://github.com/kohsuke/jenkins.git\"")
@Examples({
        "broadcast http transport url of last repository"
})
@Since("1.0")

public class ExprHttpTransportURLOfRepository extends SimplePropertyExpression<GHRepository, String> {

    static {
        register(ExprHttpTransportURLOfRepository.class, String.class,
                "[the] [skron] [http] transport url", "repository");
    }

    @Override
    public String convert( GHRepository repo) {
        return repo.getHttpTransportUrl();
    }

    @Override
    protected String getPropertyName() {
        return "http transport url";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
