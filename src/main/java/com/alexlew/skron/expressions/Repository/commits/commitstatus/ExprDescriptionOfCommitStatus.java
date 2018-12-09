package com.alexlew.skron.expressions.Repository.commits.commitstatus;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHCommitStatus;

@Name("Description of CommitStatus")
@Description("Returns the description of a CommitStatus")
@Examples({
        "broadcast description of {_status}%"
})
@Since("1.0")

public class ExprDescriptionOfCommitStatus extends SimplePropertyExpression<GHCommitStatus, String> {

    static {
        register(ExprDescriptionOfCommitStatus.class, String.class,
                "[the] [skron] description", "commitinfo");
    }

    @Override
    public String convert( GHCommitStatus status) {
        return status.getDescription();
    }

    @Override
    protected String getPropertyName() {
        return "description";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
