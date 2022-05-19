package me.karlito1501.smpcore.listeners;

import me.karlito1501.smpcore.SmpCore;
import me.karlito1501.smpcore.podels.PlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.sql.SQLException;


public class PlayerDeathListener implements Listener {

    private final SmpCore plugin;

    public PlayerDeathListener(SmpCore plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        PlayerStats stats = null;
        try {
            stats = this.plugin.getDatabase().findPlayerStatsByUUID(player.getUniqueId().toString());

            if (stats == null) {

                stats = new PlayerStats(player.getUniqueId().toString(), 0, 0, 0, 0);

                this.plugin.getDatabase().createPlayerStats(stats);

            } else {

                stats.setDeaths(stats.getDeaths() + 1);
                this.plugin.getDatabase().updatePlayerStats(stats);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



}
