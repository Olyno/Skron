package com.olyno.skron.skript.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

@Name("Repositories of Github User")
@Description("Returns public repositories of a github user.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprRepositoriesOfUser extends MultiplePropertyExpression<GitHub, GHRepository> {

    static {
        register(ExprRepositoriesOfUser.class, GHRepository.class,
                "repositories", "githubaccount"
        );
    }

    @Override
    protected GHRepository[] convert(GitHub account) {
        return account.listAllPublicRepositories().asList().toArray(new GHRepository[0]);
    }

    @Override
    public Class<? extends GHRepository> getReturnType() {
        return GHRepository.class;
    }

    @Override
    protected String getPropertyName() {
        return "repositories";
    }

}
