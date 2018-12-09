package com.alexlew.skron.expressions.ConnectionsType;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alexlew.skron.types.ConnectionType;
import org.bukkit.event.Event;

@Name("Connection type with anonymously")
@Description("Return a connection type with anonymously")
@Examples({
        "login to github with anonymously"
})
@Since("1.0")

public class ExprWithAnonymously extends SimpleExpression<ConnectionType> {

    static {
        Skript.registerExpression(ExprWithAnonymously.class, ConnectionType.class, ExpressionType.SIMPLE,
                "anonymously");
    }

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        return true;
    }

    @Override
    protected ConnectionType[] get( Event e ) {
        ConnectionType connection = new ConnectionType();
        connection.setType("with_anonymously");
        return new ConnectionType[] {connection};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends ConnectionType> getReturnType() {
        return ConnectionType.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "the anonymously connection";
    }

}
