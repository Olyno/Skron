package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;

@Name("Private state of repository")
@Description("Returns the private state of a repository. Can be set in a repository scope")
@Examples({
        "make new repository:",
        "\tset private state of repository to true"
})
@Since("1.0")

public class ExprPrivateStateOfRepository extends SimplePropertyExpression<Repository, Boolean> {

    static {
        register(ExprPrivateStateOfRepository.class, Boolean.class,
                "[the] private state", "repositorybuilder");
    }

    @Override
    public Boolean convert(Repository repo) {
        return repo.getPrivateState();
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
                    repo.setPrivateState((Boolean) delta[0]);
                    break;
                case DELETE:
                    repo.setPrivateState(null);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "private state";
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }
}
