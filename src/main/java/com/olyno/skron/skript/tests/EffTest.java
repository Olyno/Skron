package com.olyno.skron.skript.tests;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.kohsuke.github.GHRepository;

public class EffTest extends Effect {

    static {
        Skript.registerEffect(EffTest.class,
                "test %repository%"
        );
    }

    private Expression<GHRepository> github;

    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        github = (Expression<GHRepository>) expr[0];
        return true;
    }

    @Override
    protected void execute(Event e) {
     /*   try {
            new OnPush(github.getSingle(e));
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "test " + github.toString(e, debug);
    }

}
