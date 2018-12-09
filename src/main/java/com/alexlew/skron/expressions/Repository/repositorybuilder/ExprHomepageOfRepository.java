package com.alexlew.skron.expressions.Repository.repositorybuilder;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;

@Name("Home page of repository")
@Description("Returns the home page of a repository. Can be set in a repository scope")
@Examples({
        "make new repository:",
        "\tset home page of repository to \"My home page link\""
})
@Since("1.0")

public class ExprHomepageOfRepository extends SimplePropertyExpression<Repository, String> {

    static {
        register(ExprHomepageOfRepository.class, String.class,
                "[the] [skron] home page", "repositorybuilder");
    }

    @Override
    public String convert(Repository repo) {
        return (String) repo.getHomepage();
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
                    repo.setHomepage((String) delta[0]);
                    break;
                case DELETE:
                    repo.setHomepage(null);
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
