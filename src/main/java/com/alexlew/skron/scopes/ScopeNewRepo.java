package com.alexlew.skron.scopes;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.alexlew.skron.types.Repository;
import com.alexlew.skron.util.EffectSection;
import org.bukkit.event.Event;

@Name("Scope Repository Creation")
@Description("Scope for repository creation")
@Examples({
        "make new repository:",
        "\tset the name of the repository to \"My Repository\"",
        "\tset the description of the repository to \"receiver@gmail.com\"",
        "\tset the home page of the repository to \"Put a url here\"",
        "\tset the issue tracker state of the repository to true",
        "\tset the downloadable state of the repository to true",
        "\tset the wiki state of the repository to false",
        "\tset the private state of the repository to false ",
        "\tset the auto init state of the repository to true",
        "\tcreate the repository"
})
@Since("1.0")

public class ScopeNewRepo extends EffectSection {

    public static Repository lastRepository;

    static {
        Skript.registerCondition(ScopeNewRepo.class,
                "(make|do|create) [a] [new] repo[sitory]");
    }

    @Override
    public boolean init( Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        if (checkIfCondition())
            return false;
        if (!hasSection()) {
            Skript.error("A Repository creation scope is useless without any content!");
            return false;
        }
        loadSection(true);
        return true;
    }

    @Override
    protected void execute( Event e ) {
        lastRepository = new Repository();
        runSection(e);
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "make new repository";
    }
}
