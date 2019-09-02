package com.olyno.skron.util;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

public abstract class SwitchEffect<T> extends Effect {

    private Boolean negated;
    private Expression<T> expr;

    protected static void register(final Class<? extends Effect> c, final String property, final String fromType) {
        Skript.registerEffect(c,
                "enable [the] " + property + " of %" + fromType + "%",
                "enable [the] %" + fromType + "%'[s] " + property,
                "disable [the] " + property + " of %" + fromType + "%",
                "disable [the] %" + fromType + "%'[s] " + property
        );
    }

    protected abstract String getPropertyName();

    protected abstract void convert(T value);

    protected Expression<T> getExpr() {
        return expr;
    }

    protected Boolean isNegated() {
        return negated;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        this.negated = (matchedPattern == 2) || (matchedPattern == 3);
        this.expr = (Expression<T>) expr[0];
        return true;
    }

    @Override
    protected void execute(Event e) {
        convert(getExpr().getSingle(e));
    }

    @Override
    public String toString(Event e, boolean debug) {
        return (isNegated() ? "disable" : "enable") + " the " + getPropertyName();
    }

}
