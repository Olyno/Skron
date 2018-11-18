package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;

@Name("Auto init state of repository")
@Description("Returns the auto init state of a repository. Can be set in a repository scope")
@Examples({
        "make new repository:",
        "\tset auto init state of repository to true"
})
@Since("1.0")

public class ExprAutoInitStateOfRepository extends SimplePropertyExpression<Repository, Boolean> {

    static {
        register(ExprAutoInitStateOfRepository.class, Boolean.class,
                "[the] [auto] init state", "repositorybuilder");
    }

    @Override
    public Boolean convert(Repository repo) {
        return repo.getInitState();
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
                    repo.setInitState((Boolean) delta[0]);
                    break;
                case DELETE:
                    repo.setInitState(null);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "auto init state";
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }
}
