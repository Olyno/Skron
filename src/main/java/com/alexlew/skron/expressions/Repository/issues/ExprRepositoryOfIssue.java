package com.alexlew.skron.expressions.Repository.issues;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Issue;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;

@Name("Repository of issue")
@Description("Returns the repository of a issue. Can be set in a repository builder")
@Examples({
        "# outside a issue builder scope",
        "set {_repository} to repository of {_issue}",
        "# inside a issue builder scope",
        "make new issue:",
        "\tset repository of issue builder to \"New issue!\""
})
@Since("1.0")

public class ExprRepositoryOfIssue extends SimplePropertyExpression<Object, GHRepository> {

    static {
        register(ExprRepositoryOfIssue.class, GHRepository.class,
                "[the] [skron] repository", "object");
    }

    @Override
    public GHRepository convert(Object issue) {
        if (issue instanceof GHIssue) {
            GHIssue i = (GHIssue) issue;
            return i.getRepository();
        } else if (issue instanceof Issue) {
            Issue i = (Issue) issue;
            return i.getRepository();
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
        for (Object issue : getExpr().getArray(e)) {
            switch (mode) {
                case SET:
                    if (issue instanceof Issue) {
                        Issue i = (Issue) issue;
                        i.setRepository((GHRepository) delta[0]);
                    }
                    break;
                case DELETE:
                    if (issue instanceof Issue) {
                        Issue i = (Issue) issue;
                        i.setRepository(null);
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
