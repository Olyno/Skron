package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHRepository;

@Name("Git Url of repository")
@Description("Returns the git url of a repository, such as \"git://github.com/kohsuke/jenkins.git\"")
@Examples({
        "broadcast git url of last repository"
})
@Since("1.0")

public class ExprGitURLOfRepository extends SimplePropertyExpression<GHRepository, String> {

    static {
        register(ExprGitURLOfRepository.class, String.class,
                "[the] [skron] git url", "repository");
    }

    @Override
    public String convert( GHRepository repo) {
        return repo.getGitTransportUrl();
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
