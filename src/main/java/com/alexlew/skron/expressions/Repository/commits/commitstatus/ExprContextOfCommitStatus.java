package com.alexlew.skron.expressions.Repository.commits.commitstatus;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHCommitStatus;

@Name("Context of CommitStatus")
@Description("Returns the context of a CommitStatus")
@Examples({
        "broadcast context of {_status}"
})
@Since("1.0")

public class ExprContextOfCommitStatus extends SimplePropertyExpression<GHCommitStatus, String> {

    static {
        register(ExprContextOfCommitStatus.class, String.class,
                "[the] [skron] context", "commitinfo");
    }

    @Override
    public String convert( GHCommitStatus status) {
        return status.getContext();
    }

    @Override
    protected String getPropertyName() {
        return "context";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
