package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;
import org.kohsuke.github.GHRepository;

@Name("Description of repository")
@Description("Returns the description of a repository. Can be set in a repository scope")
@Examples({
        "make new repository:",
        "\tset description of repository to \"My Repository\"",
        "",
        "# or:",
        "",
        "broadcast description of repository with name \"Test\""
})
@Since("1.0")

public class ExprDescriptionOfRepository extends SimplePropertyExpression<Object, String> {

    static {
        register(ExprDescriptionOfRepository.class, String.class,
                "[the] [skron] description", "object");
    }

    @Override
    public String convert(Object repo) {
        if (repo instanceof Repository) {
            Repository r = (Repository) repo;
            return r.getDescription();
        } else if (repo instanceof GHRepository) {
            GHRepository r = (GHRepository) repo;
            return r.getDescription();
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
                        r.setDescription((String) delta[0]);
                    }
                    break;
                case DELETE:
                    if (repo instanceof Repository) {
                        Repository r = (Repository) repo;
                        r.setDescription(null);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "description";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
