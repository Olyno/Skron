package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;

@Name("Commits of Repository")
@Description("Returns commits of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprCommitsOfRepository extends MultiplePropertyExpression<GHRepository, GHCommit> {

    static {
        register(ExprCommitsOfRepository.class, GHCommit.class,
                "commits", "repository"
        );
    }

    @Override
    protected GHCommit[] convert(GHRepository repository) {
        return repository.listCommits().asList().toArray(new GHCommit[0]);
    }

    @Override
    public Class<? extends GHCommit> getReturnType() {
        return GHCommit.class;
    }

    @Override
    protected String getPropertyName() {
        return "commits";
    }

}
