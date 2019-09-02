package com.olyno.skron.skript.expressions.repository.repositorybuilder;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.olyno.skron.util.classes.RepositoryBuilder;
import org.bukkit.event.Event;

@Name("Private state of Repository Builder")
@Description("Returns private state of a repository builder. Can be set."
        + "The object type must be a repository builder.\n"
        + "The value must be a Boolean (true or false).\n"
        + "The private state will make your repository private.\n\n"
        + "Default: false"
)
@Examples({
        "on skript load:\n" +
                "\tlogin to github account \"olyno\" with oauth \"my secret oauth\"\n" +
                "\n" +
                "command create:\n" +
                "\ttrigger:\n" +
                "\t\tmake new repository named \"test\":\n" +
                "\t\t\tset private state of repository to true\n" +
                "\t\tcreate repository \"test\"\n" +
                "\t\tsend \"Repository created in private!\""
})
@Since("1.0.0")

public class ExprPrivateOfRepositoryBuilder extends SimplePropertyExpression<RepositoryBuilder, Boolean> {

    static {
        register(ExprPrivateOfRepositoryBuilder.class, Boolean.class,
                "private state", "repositorybuilder"
        );
    }

    @Override
    public Boolean convert(RepositoryBuilder repository) {
        return repository.isPrivate();
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        switch (mode) {
            case SET:
            case DELETE:
            case RESET:
                return new Class[]{Boolean.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        for (Object o : getExpr().getArray(e)) {
            RepositoryBuilder repository = (RepositoryBuilder) o;
            switch (mode) {
                case SET:
                    repository.isPrivate((Boolean) delta[0]);
                    break;
                case RESET:
                case DELETE:
                    repository.isPrivate(true);
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
