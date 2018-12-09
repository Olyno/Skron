package com.alexlew.skron.expressions.Repository.commits.commitstatus;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHCommitState;
import org.kohsuke.github.GHCommitStatus;

@Name("state of CommitStatus")
@Description("Returns the state of a CommitStatus")
@Examples({
        "set {_state} to state of {_status}%"
})
@Since("1.0")

public class ExprStateOfCommitStatus extends SimplePropertyExpression<GHCommitStatus, GHCommitState> {

    static {
        register(ExprStateOfCommitStatus.class, GHCommitState.class,
                "[the] [skron] state", "commitinfo");
    }

    @Override
    public GHCommitState convert( GHCommitStatus status) {
        return status.getState();
    }

    @Override
    protected String getPropertyName() {
        return "state";
    }

    @Override
    public Class<? extends GHCommitState> getReturnType() {
        return GHCommitState.class;
    }
}
