package com.alexlew.skron.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Commit;
import com.alexlew.skron.types.Issue;
import org.bukkit.event.Event;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;

@Name("Repository of issue/commit")
@Description("Returns the repository of a issue/commit. Can be set in a repository builder")
@Examples({
        "# outside a builder scope",
        "set {_repository} to repository of {_something}",
        "# inside a builder scope",
        "make new issue:",
        "\tset repository of issue builder to \"New issue!\""
})
@Since("1.0")

public class ExprRepositoryOfSomething extends SimplePropertyExpression<Object, GHRepository> {

    static {
        register(ExprRepositoryOfSomething.class, GHRepository.class,
                "[the] [skron] repository", "object");
    }

    @Override
    public GHRepository convert(Object o) {
        if (o instanceof GHIssue) {
            GHIssue issue = (GHIssue) o;
            return issue.getRepository();
        } else if (o instanceof Issue) {
            Issue issue = (Issue) o;
            return issue.getRepository();
        } else if (o instanceof GHCommit) {
            GHCommit commit = (GHCommit) o;
            return commit.getOwner();
        } else if (o instanceof Commit) {
            Commit commit = (Commit) o;
            return commit.getRepository();
        }
        return null;

    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE) {
            return new Class[]{Boolean.class};
        }
        return null;
    }

    @Override
    public void change( Event e, Object[] delta, Changer.ChangeMode mode) {
        for (Object o : getExpr().getArray(e)) {
            switch (mode) {
                case SET:
                    if (o instanceof Issue) {
                        Issue issue = (Issue) o;
                        issue.setRepository((GHRepository) delta[0]);
                    } else if (o instanceof Commit) {
                        Commit commit = (Commit) o;
                        commit.setRepository((GHRepository) delta[0]);
                    }
                    break;
                case DELETE:
                    if (o instanceof Issue) {
                        Issue issue = (Issue) o;
                        issue.setRepository(null);
                    } else if (o instanceof Commit) {
                        Commit commit = (Commit) o;
                        commit.setRepository(null);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "repository";
    }

    @Override
    public Class<? extends GHRepository> getReturnType() {
        return GHRepository.class;
    }
}
