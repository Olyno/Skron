package com.olyno.skron.skript.expressions.repository;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.olyno.skron.skript.effects.EffLogin;
import org.bukkit.event.Event;
import org.kohsuke.github.GHPersonSet;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;

import java.io.IOException;

@Name("Repository")
@Description("Returns an existing repository from its name."
)
@Examples({
        ""
})
@Since("1.0.0")

public class ExprRepository extends SimpleExpression<GHRepository> {

    static {
        Skript.registerExpression(ExprRepository.class, GHRepository.class, ExpressionType.SIMPLE,
                "[the] repo[sitory] [(with name|named)] %string% [(from|of) %-githubaccount%]"
        );
    }

    private Expression<String> repositoryName;
    private Expression<GitHub> account;
    private GitHub user;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        repositoryName = (Expression<String>) expr[0];
        account = (Expression<GitHub>) expr[1];
        return true;
    }

    @Override
    protected GHRepository[] get(Event e) {
        String name = repositoryName.getSingle(e);
        user = account != null ? account.getSingle(e) : EffLogin.accounts.values().iterator().next();
        try {
            return new GHRepository[]{user.getMyself().getRepository(name)};
        } catch (IOException ex) {
            Skript.exception(ex);
        }
        return null;
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        switch (mode) {
            case REMOVE:
            case REMOVE_ALL:
            case ADD:
                return new Class[]{String.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        for (Object o : delta) {
            switch (mode) {

                case ADD:
                    if (o instanceof GHUser) {
                        String name = repositoryName != null ? repositoryName.getSingle(e) : null;
                        try {
                            user.getMyself().getRepository(name).addCollaborators((GHUser) o);
                        } catch (IOException ex) {
                            Skript.exception(ex);
                        }
                    }

                case REMOVE:
                    if (o instanceof GHUser) {
                        String name = repositoryName != null ? repositoryName.getSingle(e) : null;
                        try {
                            user.getMyself().getRepository(name).removeCollaborators((GHUser) o);
                        } catch (IOException ex) {
                            Skript.exception(ex);
                        }
                    }

                case REMOVE_ALL:
                    if (o instanceof GHUser) {
                        String name = repositoryName != null ? repositoryName.getSingle(e) : null;
                        try {
                            GHRepository repository = user.getMyself().getRepository(name);
                            GHPersonSet<GHUser> users = repository.getCollaborators();
                            repository.removeCollaborators(users);
                        } catch (IOException ex) {
                            Skript.exception(ex);
                        }
                    }

            }
        }
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends GHRepository> getReturnType() {
        return GHRepository.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        try {
            return "the repository " + repositoryName.toString(e, debug) + " from github account " + (account != null ? account.toString(e, debug) : user.getMyself().getName());
        } catch (IOException | NullPointerException ex) {
            return "the repository " + repositoryName.toString(e, debug) + " from unknown github account";
        }
    }

}
