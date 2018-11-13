package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;

@Name("Wiki state of Repository")
@Description("Returns the wiki state of a Repository. Can be set in a Repository scope")
@Examples({
        "make new Repository:",
        "\tset wiki state of Repository to false"
})
@Since("1.0")

public class ExprWikiStateOfRepository extends SimplePropertyExpression<Repository, Boolean> {

    static {
        register(ExprWikiStateOfRepository.class, Boolean.class,
                "wiki state", "Repository");
    }

    @Override
    public Boolean convert(Repository repo) {
        return repo.getWikiState();
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
                    repo.setWikiState((Boolean) delta[0]);
                    break;
                case DELETE:
                    repo.setWikiState(null);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "wiki state";
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }
}
