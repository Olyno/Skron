package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHInvitation;
import org.kohsuke.github.GHRepository;

@Name("Invitations of Repository")
@Description("Returns all invitations of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprInvitationsOfRepository extends MultiplePropertyExpression<GHRepository, GHInvitation> {

    static {
        register(ExprInvitationsOfRepository.class, GHInvitation.class,
                "invitations", "repository"
        );
    }

    @Override
    protected GHInvitation[] convert(GHRepository repository) {
        return repository.listInvitations().asList().toArray(new GHInvitation[0]);
    }

    @Override
    public Class<? extends GHInvitation> getReturnType() {
        return GHInvitation.class;
    }

    @Override
    protected String getPropertyName() {
        return "invitations";
    }

}
