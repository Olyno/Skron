package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;

@Name("Issue tracker state of repository")
@Description("Returns the issue tracker state of a repository. Can be set in a repository scope")
@Examples({
        "make new repository:",
        "\tset issue tracker state of repository to true"
})
@Since("1.0")

public class ExprIssueTrackerStateOfRepository extends SimplePropertyExpression<Repository, Boolean> {

    static {
        register(ExprIssueTrackerStateOfRepository.class, Boolean.class,
                "[the] issue tracker state", "repositorybuilder");
    }

    @Override
    public Boolean convert(Repository repo) {
        return repo.getIssueTrackerState();
    }

    @Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.DELETE) {
            return new Class[]{Boolean.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, ChangeMode mode) {
        for (Repository repo : getExpr().getArray(e)) {
            switch (mode) {
                case SET:
                    repo.setIssueTrackerState((Boolean) delta[0]);
                    break;
                case DELETE:
                    repo.setIssueTrackerState(null);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "issue tracker state";
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }
}
