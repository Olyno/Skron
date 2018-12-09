package com.alexlew.skron.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;

import java.io.IOException;

@Name("Repoitory has download")
@Description("Check if the repository has downloads")
@Examples({
        "command test:",
        "\ttrigger:",
        "\t\tif last repository has downloads:",
        "\t\t\tbroadcast \"Yeah, it has downloads!\""
})
@Since("1.0")

public class CondRepositoryHasDownload extends Condition {

    static {
        Skript.registerCondition(CondRepositoryHasDownload.class,
                "%githubuser% (is|has) assigne(d|e) to %repository%"
                );
    }

    private Expression<GHUser> user;
    private Expression<GHRepository> repository;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        user = (Expression<GHUser>) expr[0];
        repository = (Expression<GHRepository>) expr[1];
        return true;
    }

    @Override
    public boolean check( Event e ) {
        try {
            if (repository.getSingle(e).hasAssignee(user.getSingle(e)))
                return true;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return user.getSingle(e) + " has assignee to " + repository.getSingle(e);
    }

}
