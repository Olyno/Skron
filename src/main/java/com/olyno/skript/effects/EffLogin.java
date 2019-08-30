package com.olyno.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.olyno.skript.events.bukkit.OnLogin;
import com.olyno.util.AsyncEffect;
import org.bukkit.event.Event;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashMap;

@Name("Login to Github")
@Description("Login to your github account.")
@Examples({
        "login to github account \"test\" with password \"abc123\""
})
@Since("1.0.0")

public class EffLogin extends AsyncEffect {

    public static HashMap<String, GitHub> accounts = new HashMap<>();

    static {
        Skript.registerEffect(EffLogin.class,
                "(login|connect) to github [account] %string% (using|with) pass[word] %string%",
                "(login|connect) to github [account] %string% (using|with) oauth [(token|code)] %string%"
        );
    }

    private Expression<String> username;
    private Expression<String> password;
    private Boolean isOauth;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        username = (Expression<String>) expr[0];
        password = (Expression<String>) expr[1];
        isOauth = matchedPattern == 1;
        return true;
    }

    @Override
    protected void execute(Event e) {
        String name = username.getSingle(e);
        String pass = password.getSingle(e);
        try {
            GitHub user;
            if (isOauth) {
                user = GitHub.connectUsingOAuth(pass);
            } else {
                user = GitHub.connectUsingPassword(name, pass);
            }
            accounts.put(name, user);
            new OnLogin(user);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "login to github account " + username.toString(e, debug) + " and password " + password.toString(e, debug);
    }

}
