package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;

@Name("Description of repository")
@Description("Returns the description of a repository. Can be set in a repository scope")
@Examples({
        "make new repository:",
        "\tset description of repository to \"My Repository\""
})
@Since("1.0")

public class ExprDescriptionOfRepository extends SimplePropertyExpression<Repository, String> {

    static {
        register(ExprDescriptionOfRepository.class, String.class,
                "[the] description", "repositorybuilder");
    }

    @Override
    public String convert(Repository repo) {
        return repo.getDescription();
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
                    repo.setDescription((String) delta[0]);
                    break;
                case DELETE:
                    repo.setDescription(null);
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
