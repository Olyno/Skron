package com.olyno.skript.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

public class ExprInstanceOf extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprInstanceOf.class, String.class, ExpressionType.SIMPLE,
                "instance of %object%"
        );
    }

    private Expression<Object> expression;

    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        expression = (Expression<Object>) expr[0];
        return true;
    }

    @Override
    protected String[] get(Event e) {
        return new String[]{expression.getSingle(e).getClass().getName()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "instance of " + expression.toString(e, debug);
    }

}
