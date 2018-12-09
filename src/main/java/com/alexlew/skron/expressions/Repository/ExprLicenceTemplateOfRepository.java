package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;

@Name("Licence template of repository")
@Description("Returns the licence template of a repository. Can be set in a repository builder scope")
@Examples({
        "make new repository:",
        "\tset licence template of repository to \"afl-3.0\""
})
@Since("1.0")

public class ExprLicenceTemplateOfRepository extends SimplePropertyExpression<Repository, String> {

    static {
        register(ExprLicenceTemplateOfRepository.class, String.class,
                "[the] [skron] licence template", "repositorybuilder");
    }

    @Override
    public String convert(Repository repo) {
        return repo.getLicenceTemplate();
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
                    repo.setLicenceTemplate((String) delta[0]);
                    break;
                case DELETE:
                    repo.setLicenceTemplate(null);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "home page";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
