package cn.codetector.minecraftplugins.gamemode4.betterFire;

import org.bukkit.plugin.java.JavaPlugin;

public class betterFireMain extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        getLogger().info("betterFire Enabled");

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
