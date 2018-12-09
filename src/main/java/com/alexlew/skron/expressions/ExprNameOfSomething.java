package com.alexlew.skron.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;

import java.io.IOException;

@Name("Name of something")
@Description("Returns the name of a something. Can be set in a scope")
@Examples({
        "make new repository:",
        "\tset name of repository to \"My Repository\""
})
@Since("1.0")

public class ExprNameOfSomething extends SimplePropertyExpression<Object, String> {

    static {
        register(ExprNameOfSomething.class, String.class,
                "[the] [skron] name", "object");
    }

    @Override
    public String convert(Object o) {
        if (o instanceof Repository) {
            Repository repository = (Repository) o;
            return repository.getName();
        } else if (o instanceof GHRepository) {
            GHRepository repository = (GHRepository) o;
            return repository.getName();
        } else if (o instanceof GHUser) {
            GHUser user = (GHUser) o;
            try {
                return user.getName();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
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
