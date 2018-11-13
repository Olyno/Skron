package com.alexlew.skron.expressions.ConnectionsType;


import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alexlew.skron.types.ConnectionType;
import org.bukkit.event.Event;

@Name("Connection type with password")
@Description("Return a connection type with password")
@Examples({
        "login to github with login \"MyGithubName\" and password \"My github password\""
})
@Since("1.0")

public class ExprWithPassword extends SimpleExpression<ConnectionType> {

    static {
        Skript.registerExpression(ExprWithPassword.class, ConnectionType.class, ExpressionType.SIMPLE,
                "(login|user[name]) %string% and [with] pass[word] %string%");
    }

    private Expression<String> username;
    private Expression<String> password;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        username = (Expression<String>) expr[0];
        password = (Expression<String>) expr[1];
        return true;
    }

    @Override
    protected ConnectionType[] get( Event e ) {
        String user = username.getSingle(e);
        String pass = password.getSingle(e);
         if (user.replaceAll(" ", "") != "" && pass.replaceAll(" ", "") != "") {
             ConnectionType connection = new ConnectionType();
             connection.setUsername(username.getSingle(e));
             connection.setPassword(password.getSingle(e));
             connection.setType("with_password");
             return new ConnectionType[] {connection};
         }
         return new ConnectionType[] {};

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
        return "the password connection";
    }

}
