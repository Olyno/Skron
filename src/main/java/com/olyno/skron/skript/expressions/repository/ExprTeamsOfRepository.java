package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHTeam;

import java.io.IOException;

@Name("Teams of Repository")
@Description("Returns teams of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprTeamsOfRepository extends MultiplePropertyExpression<GHRepository, GHTeam> {

    static {
        register(ExprTeamsOfRepository.class, GHTeam.class,
                "teams", "repository"
        );
    }

    @Override
    protected GHTeam[] convert(GHRepository repository) {
        try {
            return repository.getTeams().toArray(new GHTeam[0]);
        } catch (IOException ex) {
            Skript.exception(ex);
        }
        return null;
    }

    @Override
    public Class<? extends GHTeam> getReturnType() {
        return GHTeam.class;
    }

    @Override
    protected String getPropertyName() {
        return "teams";
    }

}
