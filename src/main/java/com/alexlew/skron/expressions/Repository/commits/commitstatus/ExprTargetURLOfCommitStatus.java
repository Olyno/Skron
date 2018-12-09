package com.alexlew.skron.expressions.Repository.commits.commitstatus;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHCommitStatus;

@Name("Target url of CommitStatus")
@Description("Returns the target url of a CommitStatus")
@Examples({
        "broadcast target url of {_status}%"
})
@Since("1.0")

public class ExprTargetURLOfCommitStatus extends SimplePropertyExpression<GHCommitStatus, String> {

    static {
        register(ExprTargetURLOfCommitStatus.class, String.class,
                "[the] [skron] [target] url", "commitinfo");
    }

    @Override
    public String convert( GHCommitStatus status) {
        return status.getTargetUrl();
    }

    @Override
    protected String getPropertyName() {
        return "target url";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
