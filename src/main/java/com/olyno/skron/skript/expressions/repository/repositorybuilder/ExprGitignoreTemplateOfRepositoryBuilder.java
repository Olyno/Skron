package com.olyno.skron.skript.expressions.repository.repositorybuilder;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.olyno.skron.util.classes.RepositoryBuilder;
import org.bukkit.event.Event;

@Name("Gitignore Template of Repository Builder")
@Description("Returns gitignore template of a repository builder. Can be set."
        + "The object type must be a repository builder.\n"
        + "The value must be a String (the language of your repository, like javascript, java etc...).\n"
        + "Will add a default .gitignore file depending the language in the repository.\n\n"
        + "Default: null"
)
@Examples({
        "on skript load:\n" +
                "\tlogin to github account \"olyno\" with oauth \"my secret oauth\"\n" +
                "\n" +
                "command create:\n" +
                "\ttrigger:\n" +
                "\t\tmake new repository named \"test\":\n" +
                "\t\t\tset gitignore template of repository to \"javascript\"\n" +
                "\t\tcreate repository \"test\"\n" +
                "\t\tsend \"Repository created!\""
})
@Since("1.0.0")

public class ExprGitignoreTemplateOfRepositoryBuilder extends SimplePropertyExpression<Object, String> {

    static {
        register(ExprGitignoreTemplateOfRepositoryBuilder.class, String.class,
                "[the] [repo[sitory]] gitignore template", "object"
        );
    }

    @Override
    public String convert(Object repository) {
        if (repository instanceof RepositoryBuilder) {
            RepositoryBuilder repo = (RepositoryBuilder) repository;
            return repo.getGitignoreTemplate();
        } else {
            return null;
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        switch (mode) {
            case SET:
            case RESET:
            case DELETE:
                return new Class[]{String.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        for (Object o : getExpr().getArray(e)) {
            RepositoryBuilder repository = (RepositoryBuilder) o;
            switch (mode) {
                case SET:
                    repository.setGitignoreTemplate((String) delta[0]);
                    break;
                case RESET:
                case DELETE:
                    repository.setGitignoreTemplate(null);

            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "gitignore template";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
