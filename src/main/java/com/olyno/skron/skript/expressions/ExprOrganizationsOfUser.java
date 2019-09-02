package com.olyno.skron.skript.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GitHub;

@Name("Organizations of Github User")
@Description("Returns organizations of a github user.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprOrganizationsOfUser extends MultiplePropertyExpression<GitHub, GHOrganization> {

    static {
        register(ExprOrganizationsOfUser.class, GHOrganization.class,
                "organizations", "githubaccount"
        );
    }

    @Override
    protected GHOrganization[] convert(GitHub account) {
        return account.listOrganizations().asList().toArray(new GHOrganization[0]);
    }

    @Override
    public Class<? extends GHOrganization> getReturnType() {
        return GHOrganization.class;
    }

    @Override
    protected String getPropertyName() {
        return "organizations";
    }

}
