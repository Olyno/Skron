package com.olyno.skron.skript.expressions.repository.repositorybuilder;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.olyno.skron.util.classes.RepositoryBuilder;
import org.bukkit.event.Event;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

@Name("Issues state of Repository")
@Description("Returns issues state of a repository or repository builder. Can be set."
        + "The object type must be a repository or a repository builder.\n"
        + "The value must be a Boolean (true or false).\n"
        + "The issue state will add or remove issues possibility in the repository.\n\n"
        + "Default: true"
)
@Examples({
        "on skript load:\n" +
                "\tlogin to github account \"olyno\" with oauth \"my secret oauth\"\n" +
                "\n" +
                "command create:\n" +
                "\ttrigger:\n" +
                "\t\tmake new repository named \"test\":\n" +
                "\t\t\tset issue state of repository to true\n" +
                "\t\tcreate repository \"test\"\n" +
                "\t\tsend \"Repository created!\""
})
@Since("1.0.0")

public class ExprIssuesOfRepositoryBuilder extends SimplePropertyExpression<Object, Boolean> {

    static {
        register(ExprIssuesOfRepositoryBuilder.class, Boolean.class,
                "issue[s] (state|tracker)", "object"
        );
    }

    @Override
    public Boolean convert(Object repository) {
        if (repository instanceof RepositoryBuilder) {
            RepositoryBuilder repo = (RepositoryBuilder) repository;
            return repo.hasIssues();
        } else if (repository instanceof GHRepository) {
            GHRepository repo = (GHRepository) repository;
            return repo.hasIssues();
        } else {
            return null;
        }
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

            if (o instanceof RepositoryBuilder) {
                RepositoryBuilder repository = (RepositoryBuilder) o;
                switch (mode) {
                    case SET:
                        repository.hasIssues((Boolean) delta[0]);
                        break;
                    case RESET:
                    case DELETE:
                        repository.hasIssues(true);
                        break;
                }

            } else if (o instanceof GHRepository) {
                GHRepository repository = (GHRepository) o;
                switch (mode) {
                    case SET:
                        try {
                            repository.enableIssueTracker((Boolean) delta[0]);
                        } catch (IOException ex) {
                            Skript.exception(ex);
                        }
                        break;
                    case RESET:
                    case DELETE:
                        try {
                            repository.enableIssueTracker(true);
                        } catch (IOException ex) {
                            Skript.exception(ex);
                        }
                        break;
                }
            }

        }
    }

    @Override
    protected String getPropertyName() {
        return "issues state";
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }
}
