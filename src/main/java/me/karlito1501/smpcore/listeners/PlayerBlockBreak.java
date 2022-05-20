package me.karlito1501.smpcore.listeners;

import it.unimi.dsi.fastutil.Hash;
import me.karlito1501.smpcore.SmpCore;
import me.karlito1501.smpcore.podels.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import static me.karlito1501.smpcore.SmpCore.MESSAGE_PREFIX_RED;


public class PlayerBlockBreak implements Listener {

    private final SmpCore plugin;


    public PlayerBlockBreak(SmpCore plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        PlayerStats stats = null;
        try {
            stats = this.plugin.getDatabase().findPlayerStatsByUUID(player.getUniqueId().toString());

            if (stats == null) {

                stats = new PlayerStats(player.getUniqueId().toString(),player.getDisplayName(),0, 0, 0,0, 0);

                this.plugin.getDatabase().createPlayerStats(stats);

            } else {
                if(block.getType() == Material.DIAMOND_ORE || block.getType() == Material.DEEPSLATE_DIAMOND_ORE){
                    stats.setDiamondsBroken(stats.getDiamondsBroken() + 1);
                    String playername = player.getDisplayName();
                    for(Player player1 : Bukkit.getServer().getOnlinePlayers()){
                        if(player1.hasPermission("smpcore.antixray")){

                            player1.sendMessage(MESSAGE_PREFIX_RED + " " + ChatColor.YELLOW + playername + ChatColor.RED + " has mined diamond ore!");
                        }
                    }
                }
                if(block.getType() == Material.STONE || block.getType() == Material.DEEPSLATE){
                    stats.setStoneBroken(stats.getStoneBroken() + 1);
                }

                stats.setBlocksBroken(stats.getBlocksBroken() + 1);
                this.plugin.getDatabase().updatePlayerStats(stats);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
