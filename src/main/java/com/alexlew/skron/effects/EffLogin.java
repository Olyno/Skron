package com.alexlew.skron.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.alexlew.skron.Skron;
import com.alexlew.skron.types.ConnectionType;
import org.bukkit.event.Event;
import org.kohsuke.github.GitHub;

import java.io.IOException;

@Name("Login to github")
@Description("Make the connection between github and your server")
@Examples({
        "login to github with login \"MyGithubName\" and oauth token \"My github password\""
})
@Since("1.0")

public class EffLogin extends Effect {

    static {
        Skript.registerEffect(EffLogin.class,
                "login to github with %connectionmethod%");
    }

    private Expression<ConnectionType> connection;

    public static ConnectionType lastGithubConnection;
    public static GitHub account;

    @Override
    public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
        connection = (Expression<ConnectionType>) expr[0];
        return true;
    }

    @Override
    protected void execute(Event e) {
        ConnectionType connect = connection.getSingle(e);
        if (connect.getType() == "with_password") {
            try {
                GitHub github = GitHub.connectUsingPassword(connect.getUsername(), connect.getPassword());
                account = github;
            } catch (IOException e1) {
                Skron.error("Username or password incorrect during the connection to github.");
                //e1.printStackTrace();
            }
        } else if (connect.getType() == "with_oauth") {
            try {
                GitHub github = GitHub.connectUsingOAuth(connect.getPassword());
                account = github;
            } catch (IOException e1) {
                Skron.error("Username or oauth token incorrect during the connection to github.");
                //e1.printStackTrace();
            }
        } else {
            try {
                GitHub github = GitHub.connectAnonymously();
                account = github;
            } catch (IOException e1) {
                Skron.error("Anonymously connection can't be used here.");
                //e1.printStackTrace();
            }
        }
        lastGithubConnection = connect;

    }

    @Override
    public String toString(Event e, boolean debug) {
        return "login to github as " + connection.getSingle(e).getUsername() +
                ", with password " + connection.getSingle(e).getPassword() +
                " using " + connection.getSingle(e).getType();
    }
}
