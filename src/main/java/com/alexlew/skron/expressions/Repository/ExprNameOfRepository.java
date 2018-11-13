package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;

@Name("Name of Repository")
@Description("Returns the name of a Repository. Can be set in a Repository scope")
@Examples({
        "make new Repository:",
        "\tset name of Repository to \"My Repository\""
})
@Since("1.0")

public class ExprNameOfRepository extends SimplePropertyExpression<Repository, String> {

    static {
        register(ExprNameOfRepository.class, String.class,
                "name", "repository");
    }

    @Override
    public String convert(Repository repo) {
        return repo.getName();
    }

    @Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.DELETE) {
            return new Class[]{String.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, ChangeMode mode) {
        for (Repository repo : getExpr().getArray(e)) {
            switch (mode) {
                case SET:
                    repo.setName((String) delta[0]);
                    break;
                case DELETE:
                    repo.setName(null);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "name";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
