package com.olyno.skript.events.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.olyno.skript.events.bukkit.OnLogin;
import org.kohsuke.github.GitHub;

public class EvtLogin {

    static {

        // On login
        Skript.registerEvent("Github connection Event", SimpleEvent.class, OnLogin.class,
                "github account (connected|logged)"
        );

        EventValues.registerEventValue(OnLogin.class, GitHub.class, new Getter<GitHub, OnLogin>() {

            @Override
            public GitHub get(OnLogin e) {
                return e.getUser();
            }
        }, 0);

    }

}
