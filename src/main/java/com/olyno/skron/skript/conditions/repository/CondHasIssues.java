package com.olyno.skron.skript.conditions.repository;

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

@Name("Has Issues")
@Description("Returns if repository has issues or not.")
@Examples({
        ""
})
@Since("1.0.0")

public class CondHasIssues extends Condition {

    static {
        Skript.registerCondition(CondHasIssues.class,
                "%repository% has issues"
        );
    }

    private Expression<GHRepository> repository;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        repository = (Expression<GHRepository>) expr[0];
        return true;
    }

    @Override
    public boolean check(Event e) {
        return repository.getSingle(e).hasIssues();
    }

    @Override
    public String toString(Event e, boolean debug) {
        return repository.toString(e, debug) + " has issues";
    }

}
