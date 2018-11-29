package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import org.kohsuke.github.GHRepository;

@Name("Name of repository")
@Description("Returns the name of a repository. Can be set in a repository scope")
@Examples({
        "make new repository:",
        "\tset name of repository to \"My Repository\""
})
@Since("1.0")

public class ExprNameOfRepository extends SimplePropertyExpression<Object, String> {

    static {
        register(ExprNameOfRepository.class, String.class,
                "[the] [skron] name", "object");
    }

    @Override
    public String convert(Object repo) {
        if (repo instanceof Repository) {
            Repository r = (Repository) repo;
            return r.getName();
        } else if (repo instanceof GHRepository) {
            GHRepository r = (GHRepository) repo;
            return r.getName();
        } else {
            return null;
        }

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
        for (Object repo : getExpr().getArray(e)) {
            switch (mode) {
                case SET:
                    if (repo instanceof Repository) {
                        Repository r = (Repository) repo;
                        r.setName((String) delta[0]);
                    }
                    break;
                case DELETE:
                    if (repo instanceof Repository) {
                        Repository r = (Repository) repo;
                        r.setName(null);
                    }
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
