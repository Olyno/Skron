package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.kohsuke.github.GHPersonSet;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;

import java.io.IOException;

@Name("Collaborators of Repository")
@Description("Returns collaborators of a repository. Can be set anywhere.")
@Examples({
        "\tadd user \"AlexLew95\" to collaborators of last repository"
})
@Since("1.0")

public class ExprCollaboratorsOfRepository extends SimpleExpression<GHUser> {

    static {
        Skript.registerExpression(ExprCollaboratorsOfRepository.class, GHUser.class,
                ExpressionType.SIMPLE,"[the] [skron] collaborators of %repository%",
                                                "[the] %repository%'s [skron] collaborators"
        );
    }

    private Expression<GHRepository> repository;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        repository = (Expression<GHRepository>) expr[0];
        return true;
    }

    @Override
    protected GHUser[] get( Event e ) {
        GHPersonSet<GHUser> users = new GHPersonSet<GHUser>();
        try {
            users = repository.getSingle(e).getCollaborators();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return users.toArray(new GHUser[users.size()]);
    }

    @Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.ADD || mode == ChangeMode.REMOVE) {
            return new Class[]{String.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, ChangeMode mode) {
        switch (mode) {
            case ADD:
                try {
                    repository.getSingle(e).addCollaborators((GHUser) delta[0]);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            case REMOVE:
                try {
                    repository.getSingle(e).removeCollaborators((GHUser) delta[0]);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            default:
                break;
            }
    }


    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends GHUser> getReturnType() {
        return GHUser.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "collaborators " + repository.getSingle(e);
    }
}
