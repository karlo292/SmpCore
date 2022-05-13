package me.karlito1501.smpcore;


import me.karlito1501.smpcore.commands.*;
import me.karlito1501.smpcore.listeners.PlayerJoinListener;
import me.karlito1501.smpcore.listeners.PlayerLeaveListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmpCore extends JavaPlugin {
    // PLEASE USE THIS :)
    public static String MESSAGE_PREFIX =ChatColor.AQUA +""+ChatColor.BOLD +"[SmpCore]"+ChatColor.AQUA;
    public static String MESSAGE_PREFIX_RED = ChatColor.RED + "" + ChatColor.BOLD + "[SmpCore]";
    public static String NO_PERMISSION = ChatColor.RED + "No permission!";

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(), this);
        this.getCommand("gmc").setExecutor(new GamemodeCreativeCommand());
        this.getCommand("gms").setExecutor(new GamemodeSurvivalCommand());
        this.getCommand("gma").setExecutor(new GamemodeAdventureCommand());
        this.getCommand("gmsp").setExecutor(new GamemodeSpectatorCommand());
        this.getCommand("day").setExecutor(new TimeDayCommand());
        this.getCommand("night").setExecutor(new TimeNightCommand());

        System.out.println("##################");
        System.out.println("#Starting SmpCore#");
        System.out.println("##################");



    }

    @Override
    public void onDisable() {
        System.out.println("###########################");
        System.out.println("###Shutting down SmpCore###");
        System.out.println("###########################");

    }
}