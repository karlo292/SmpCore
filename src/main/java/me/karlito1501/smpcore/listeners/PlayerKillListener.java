package me.karlito1501.smpcore.listeners;

import me.karlito1501.smpcore.SmpCore;
import me.karlito1501.smpcore.podels.PlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;

public class PlayerKillListener implements Listener{

    private final SmpCore plugin;

    public PlayerKillListener(SmpCore plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onKill(EntityDeathEvent event) {
        if(event.getEntity().getKiller() instanceof Player){
            Player player = event.getEntity().getKiller();

                PlayerStats stats = null;
                try {
                    stats = this.plugin.getDatabase().findPlayerStatsByUUID(player.getUniqueId().toString());

                    if (stats == null) {

                        stats = new PlayerStats(player.getUniqueId().toString(), player.getDisplayName(),0, 0, 0, 0,0);

                        this.plugin.getDatabase().createPlayerStats(stats);

                    } else {

                        stats.setKills(stats.getKills() + 1);
                        this.plugin.getDatabase().updatePlayerStats(stats);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }



        }


    }


}
