package com.alexlew.skron;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Skron extends JavaPlugin {

    static Skron instance;
    SkriptAddon addon;

    public void onEnable() {
        try {
            getAddonInstance().loadClasses("com.alexlew.skron", "effects", "events", "expressions", "scopes", "types");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Metrics metrics = new Metrics(this);
        metrics.addCustomChart(new Metrics.SimplePie("used_language", () ->
                getConfig().getString("language", "en")));

        metrics.addCustomChart(new Metrics.DrilldownPie("java_version", () -> {
            Map<String, Map<String, Integer>> map = new HashMap<>();
            String javaVersion = System.getProperty("java.version");
            Map<String, Integer> entry = new HashMap<>();
            entry.put(javaVersion, 1);
            if (javaVersion.startsWith("1.7")) {
                map.put("Java 1.7", entry);
            } else if (javaVersion.startsWith("1.8")) {
                map.put("Java 1.8", entry);
            } else if (javaVersion.startsWith("1.9")) {
                map.put("Java 1.9", entry);
            } else {
                map.put("Other", entry);
            }
            return map;
        }));
    }

    public static void error(String error) {
        System.out.println("\u001B[31m[Skron] " + error + "\u001B[0m");
    }

    public static Skron getInstance() {
        return instance;
    }

    public Skron() {
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException();
        }

    }

    public SkriptAddon getAddonInstance() {
        if (addon == null) {
            addon = Skript.registerAddon(getInstance())
                    .setLanguageFileDirectory("lang");
        }
        return addon;
    }
}