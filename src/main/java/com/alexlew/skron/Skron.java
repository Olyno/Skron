package com.alexlew.skron;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alexlew.skron.util.Registration;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

public class Skron extends JavaPlugin {

    static Skron instance;
    SkriptAddon addon;
    public List<Registration> expressions = new ArrayList<>();

    public void onEnable() {
        instance = this;
        addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("com.alexlew.skron", "effects", "expressions", "scopes", "types");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void error(String error) {
        System.out.println("\u001B[31m[Skron] " + error + "\u001B[0m");
    }

    public static Skron getInstance() {
        return instance;
    }

    public SkriptAddon getAddonInstance() {
        if (addon == null) {
            addon = Skript.registerAddon(getInstance())
                    .setLanguageFileDirectory("lang");
        }
        return addon;
    }
}