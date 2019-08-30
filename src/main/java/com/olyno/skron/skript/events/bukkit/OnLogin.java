package com.olyno.skron.skript.events.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.kohsuke.github.GitHub;

public class OnLogin extends Event implements Listener {

    public static final HandlerList handlers = new HandlerList();

    private GitHub user;

    public OnLogin(GitHub login) {
        this.user = login;
        Bukkit.getServer().getPluginManager().callEvent(this);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public GitHub getUser() {
        return user;
    }
}
