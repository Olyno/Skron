package com.olyno.skron.skript.expressions.repository.milestones;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.olyno.skron.util.MultiplePropertyExpression;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHMilestone;
import org.kohsuke.github.GHRepository;

@Name("Opened Milestones of Repository")
@Description("Returns opened milestones of a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprOpenedMilestonesOfRepository extends MultiplePropertyExpression<GHRepository, GHMilestone> {

    static {
        register(ExprOpenedMilestonesOfRepository.class, GHMilestone.class,
                "opened milestones", "repository"
        );
    }

    @Override
    protected GHMilestone[] convert(GHRepository repository) {
        return repository.listMilestones(GHIssueState.OPEN).asList().toArray(new GHMilestone[0]);
    }

    @Override
    public Class<? extends GHMilestone> getReturnType() {
        return GHMilestone.class;
    }

    @Override
    protected String getPropertyName() {
        return "opened milestones";
    }

}
