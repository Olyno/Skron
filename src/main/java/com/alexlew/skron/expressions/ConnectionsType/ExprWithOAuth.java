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

@Name("Connection type with oauth")
@Description("Return a connection type with oauth")
@Examples({
        "login to github with login \"my login\" and oauth token \"My github password\""
})
@Since("1.0")

public class ExprWithOAuth extends SimpleExpression<ConnectionType> {

    static {
        Skript.registerExpression(ExprWithOAuth.class, ConnectionType.class, ExpressionType.SIMPLE,
                "(login|user[name]) %string% and [with] oauth [token] %string%");
    }

    private Expression<String> username;
    private Expression<String> oauth_token;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        username = (Expression<String>) expr[0];
        oauth_token = (Expression<String>) expr[1];
        return true;
    }

    @Override
    protected ConnectionType[] get( Event e ) {
        String user = username.getSingle(e);
        String oauth = oauth_token.getSingle(e);
        if (user.replaceAll(" ", "") != "" && oauth.replaceAll(" ", "") != "") {
            ConnectionType connection = new ConnectionType();
            connection.setUsername(username.getSingle(e));
            connection.setPassword(oauth_token.getSingle(e));
            connection.setType("with_oauth");
            return new ConnectionType[]{connection};
        }
        return new ConnectionType[] { };

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
        return "the oauth token connection";
    }

}
