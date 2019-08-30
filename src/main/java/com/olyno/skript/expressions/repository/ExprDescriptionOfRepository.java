package com.olyno.skript.expressions.repository;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.olyno.util.classes.RepositoryBuilder;
import org.bukkit.event.Event;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

@Name("Description of Repository")
@Description("Returns description of a repository or repository builder. Can be set.")
@Examples({
        ""
})
@Since("1.0.0")

public class ExprDescriptionOfRepository extends SimplePropertyExpression<Object, String> {

    static {
        register(ExprDescriptionOfRepository.class, String.class,
                "[the] [repo[sitory]] description", "object"
        );
    }

    @Override
    public String convert(Object repository) {
        if (repository instanceof RepositoryBuilder) {
            RepositoryBuilder repo = (RepositoryBuilder) repository;
            return repo.getDescription();
        } else if (repository instanceof GHRepository) {
            GHRepository repo = (GHRepository) repository;
            return repo.getDescription();
        } else {
            return null;
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        switch (mode) {
            case DELETE:
            case RESET:
            case SET:
                return new Class[]{String.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        for (Object o : getExpr().getArray(e)) {
            Object repository = o;

            if (repository instanceof RepositoryBuilder) {
                RepositoryBuilder repo = (RepositoryBuilder) repository;
                switch (mode) {
                    case SET:
                        repo.setDescription((String) delta[0]);
                        break;
                    case RESET:
                    case DELETE:
                        repo.setDescription(null);
                        break;
                }

            } else if (repository instanceof GHRepository) {
                GHRepository repo = (GHRepository) repository;
                switch (mode) {
                    case SET:
                        try {
                            repo.setDescription((String) delta[0]);
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
        return "description";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
