package com.olyno.skron.skript.expressions.repository.repositorybuilder;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.olyno.skron.util.classes.RepositoryBuilder;
import org.bukkit.event.Event;

@Name("Merge Commit state of Repository Builder")
@Description("Returns merge commit state of a repository builder. Can be set."
        + "The object type must be a repository builder.\n"
        + "The value must be a Boolean (true or false).\n"
        + "The merge commit state will accept or not commits in the repository.\n\n"
        + "Default: true"
)
@Examples({
        "on skript load:\n" +
                "\tlogin to github account \"olyno\" with oauth \"my secret oauth\"\n" +
                "\n" +
                "command create:\n" +
                "\ttrigger:\n" +
                "\t\tmake new repository named \"test\":\n" +
                "\t\t\tset merge commit state of repository to true\n" +
                "\t\tcreate repository \"test\"\n" +
                "\t\tsend \"Repository created!\""
})
@Since("1.0.0")

public class ExprMergeCommitOfRepositoryBuilder extends SimplePropertyExpression<Object, Boolean> {

    static {
        register(ExprMergeCommitOfRepositoryBuilder.class, Boolean.class,
                "[the] [repo[sitory]] merge commit[s] state", "object"
        );
    }

    @Override
    public Boolean convert(Object repository) {
        if (repository instanceof RepositoryBuilder) {
            RepositoryBuilder repo = (RepositoryBuilder) repository;
            return repo.allowMergeCommit();
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
            RepositoryBuilder repository = (RepositoryBuilder) o;
            switch (mode) {
                case SET:
                    repository.allowMergeCommit((Boolean) delta[0]);
                    break;
                case RESET:
                case DELETE:
                    repository.allowMergeCommit(true);
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "merge commit state";
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }
}
