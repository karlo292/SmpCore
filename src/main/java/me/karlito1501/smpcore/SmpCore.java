package me.karlito1501.smpcore;


import me.karlito1501.smpcore.listeners.PlayerJoinListener;
import me.karlito1501.smpcore.listeners.PlayerLeaveListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmpCore extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(), this);
        System.out.println("##################");
        System.out.println("#Starting SmpEasy#");
        System.out.println("##################");

    }

    @Override
    public void onDisable() {
        System.out.println("###########################");
        System.out.println("###Shutting down SmpEasy###");
        System.out.println("###########################");

    }
}