package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.event.Event;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

@Name("Default Branch of Repository")
@Description("Returns default branch of a repository. Can be set.")
@Examples({
        "on skript load:\n" +
                "\tlogin to github account \"olyno\" with oauth \"my secret oauth\"\n" +
                "\n" +
                "command default branch:\n" +
                "\ttrigger:\n" +
                "\t\tset default branch of repository \"test\" to \"master\"\n" +
                "\t\tsend \"Default branch changed!\""
})
@Since("1.0.0")

public class ExprDefaultBranchOfRepository extends SimplePropertyExpression<GHRepository, String> {

    static {
        register(ExprDefaultBranchOfRepository.class, String.class,
                "[the] default branch", "repository"
        );
    }

    @Override
    public String convert(GHRepository repository) {
        return repository.getDefaultBranch();
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        switch (mode) {
            case SET:
                return new Class[]{String.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        for (Object o : getExpr().getArray(e)) {
            GHRepository repository = (GHRepository) o;

            switch (mode) {
                case SET:
                    try {
                        repository.setDefaultBranch((String) delta[0]);
                    } catch (IOException ex) {
                        Skript.exception(ex);
                    }
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
