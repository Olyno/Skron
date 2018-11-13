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
@Description("Scope for Repository creation")
@Examples({
        "make new Repository:",
        "\tset the name of the Repository to \"My Repository\"",
        "\tset the description of the Repository to \"receiver@gmail.com\"",
        "\tset the home page of the Repository to \"Put a url here\"",
        "\tset the issue tracker state of the Repository to true",
        "\tset the downloadable state of the Repository to true",
        "\tset the wiki state of the Repository to false",
        "\tset the private state of the Repository to false ",
        "\tset the auto init state of the Repository to true",
        "\tcreate the Repository"
})
@Since("1.0")

public class ScopeNewRepo extends EffectSection {

    public static Repository lastRepository;

    static {
        Skript.registerCondition(ScopeNewRepo.class,
                "(make|do|create) [new] repo[sitory]");
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
        return "make new Repository";
    }
}
