package com.alexlew.skron.expressions.Repository.commits.commitstatus;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHCommitStatus;
import org.kohsuke.github.GHUser;

import java.io.IOException;

@Name("Creator of CommitStatus")
@Description("Returns the creator of a CommitStatus")
@Examples({
        "broadcast \"%creator of {_status}%\""
})
@Since("1.0")

public class ExprCreatorOfCommitStatus extends SimplePropertyExpression<GHCommitStatus, GHUser> {

    static {
        register(ExprCreatorOfCommitStatus.class, GHUser.class,
                "[the] [skron] creator", "commitinfo");
    }

    @Override
    public GHUser convert( GHCommitStatus status) {
        try {
            return status.getCreator();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "creator";
    }

    @Override
    public Class<? extends GHUser> getReturnType() {
        return GHUser.class;
    }
}
