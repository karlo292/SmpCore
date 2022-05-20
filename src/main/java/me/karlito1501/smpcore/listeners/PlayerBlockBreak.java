package me.karlito1501.smpcore.listeners;

import me.karlito1501.smpcore.SmpCore;
import me.karlito1501.smpcore.podels.PlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.sql.SQLException;


public class PlayerBlockBreak implements Listener {

    private final SmpCore plugin;

    public PlayerBlockBreak(SmpCore plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        PlayerStats stats = null;
        try {
            stats = this.plugin.getDatabase().findPlayerStatsByUUID(player.getUniqueId().toString());

            if (stats == null) {

                stats = new PlayerStats(player.getUniqueId().toString(), 0, 0, 0);

                this.plugin.getDatabase().createPlayerStats(stats);

            } else {

                stats.setBlocksBroken(stats.getBlocksBroken() + 1);
                this.plugin.getDatabase().updatePlayerStats(stats);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Long blocksBroken = stats.getBlocksBroken();

    }

}
