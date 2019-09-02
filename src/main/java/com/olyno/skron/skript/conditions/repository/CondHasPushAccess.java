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

@Name("Has Push Access")
@Description("Returns if repository has push access or not.")
@Examples({
        ""
})
@Since("1.0.0")

public class CondHasPushAccess extends Condition {

    static {
        Skript.registerCondition(CondHasPushAccess.class,
                "%repository% has push access"
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
        return repository.getSingle(e).hasPushAccess();
    }

    @Override
    public String toString(Event e, boolean debug) {
        return repository.toString(e, debug) + " has push access";
    }

}
