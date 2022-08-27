package net.reinosmc.besttownywar;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getLogger().info("BestTownyWar enabled successfully | ReinosMC");
        getLogger().info("Made by Red Sierra Studios <3");

    }

    @Override
    public void onLoad() {
        getLogger().info("BestTownyWar loaded correctly");
    }
    @Override
    public void onDisable() {
        getLogger().info("BestTownyWar disabled");
    }
}