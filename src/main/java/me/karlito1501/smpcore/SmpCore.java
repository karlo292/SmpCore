package me.karlito1501.smpcore;


import me.karlito1501.smpcore.commands.*;
import me.karlito1501.smpcore.db.Database;
import me.karlito1501.smpcore.listeners.PlayerBlockBreak;
import me.karlito1501.smpcore.listeners.PlayerJoinListener;
import me.karlito1501.smpcore.listeners.PlayerKillListener;
import me.karlito1501.smpcore.listeners.PlayerLeaveListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class SmpCore extends JavaPlugin {

    private Database database;

    // PLEASE USE THIS :)
    public static String MESSAGE_PREFIX =ChatColor.AQUA +""+ChatColor.BOLD +"[SmpCore]"+ChatColor.AQUA;
    public static String MESSAGE_PREFIX_RED = ChatColor.RED + "" + ChatColor.BOLD + "[SmpCore]" + ChatColor.RED;
    public static String NO_PERMISSION = ChatColor.RED + "No permission!";

    @Override
    public void onEnable() {
        try {
            this.database = new Database();
            database.initializeDatabase();
        }catch (SQLException ex){

            System.out.println("Unable to connect to database and create tables!");

            ex.printStackTrace();

        }


        getServer().getPluginManager().registerEvents(new PlayerKillListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerBlockBreak(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(), this);
        this.getCommand("gmc").setExecutor(new GamemodeCreativeCommand());
        this.getCommand("gms").setExecutor(new GamemodeSurvivalCommand());
        this.getCommand("gma").setExecutor(new GamemodeAdventureCommand());
        this.getCommand("gmsp").setExecutor(new GamemodeSpectatorCommand());
        this.getCommand("day").setExecutor(new TimeDayCommand());
        this.getCommand("night").setExecutor(new TimeNightCommand());
        this.getCommand("tp").setExecutor(new TpCommand());


        System.out.println("##################");
        System.out.println("#Starting SmpCore#");
        System.out.println("##################");


    }

    public Database getDatabase() {
        return database;
    }
}