package com.olyno.skron.skript.expressions.repository;

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

@Name("Name of Repository")
@Description("Returns name of a repository or a repository builder. Can be set.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprNameOfRepository extends SimplePropertyExpression<Object, String> {

    static {
        register(ExprNameOfRepository.class, String.class,
                "repo[sitory] name", "object"
        );
    }

    @Override
    public String convert(Object repository) {
        if (repository instanceof RepositoryBuilder) {
            RepositoryBuilder repo = (RepositoryBuilder) repository;
            return repo.getName();
        } else if (repository instanceof GHRepository) {
            GHRepository repo = (GHRepository) repository;
            return repo.getName();
        } else {
            return null;
        }
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
            Object repository = delta[0];

            if (repository instanceof RepositoryBuilder) {
                RepositoryBuilder repo = (RepositoryBuilder) repository;
                switch (mode) {
                    case SET:
                        repo.setName((String) o);
                        break;
                }

            } else if (repository instanceof GHRepository) {
                GHRepository repo = (GHRepository) repository;
                switch (mode) {
                    case SET:
                        try {
                            repo.renameTo((String) o);
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
        return "repository name";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
