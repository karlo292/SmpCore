package me.karlito1501.smpcore.commands;

import me.karlito1501.smpcore.SmpCore;
import me.karlito1501.smpcore.podels.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

import static me.karlito1501.smpcore.SmpCore.*;

public class XrayCheck implements CommandExecutor {

    private final SmpCore plugin;

    public XrayCheck(SmpCore plugin) {
        this.plugin = plugin;
    }



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){

            if(!player.hasPermission("smpcore.xraycheck")){
                player.sendMessage(NO_PERMISSION);
                return true;
            }
            if(args.length == 0){
                player.sendMessage(MESSAGE_PREFIX_RED + " Usage: /xraycheck <player>");
                return true;
            }
            PlayerStats stats = null;
            Player target = Bukkit.getPlayer(args[0]);

            try {
                stats = this.plugin.getDatabase().findPlayerStatsByUUID(player.getUniqueId().toString());

                if (stats == null) {

                    stats = new PlayerStats(player.getUniqueId().toString(), player.getDisplayName(),0, 0, 0, 0,0);

                    this.plugin.getDatabase().createPlayerStats(stats);

                } else {

                    String stone = String.valueOf(stats.getStoneBroken());
                    String diamonds = String.valueOf(stats.getDiamondsBroken());
                    String others = String.valueOf(stats.getBlocksBroken());

                    player.sendMessage(MESSAGE_PREFIX + ChatColor.YELLOW + "Suspect has broken total of:");
                    player.sendMessage(ChatColor.YELLOW + "             Stone: " + ChatColor.RESET + stone);
                    player.sendMessage(ChatColor.YELLOW + "             Diamonds: " + ChatColor.RESET + diamonds);
                    player.sendMessage(ChatColor.YELLOW + "             All blocks: " + ChatColor.RESET + others);

                    this.plugin.getDatabase().updatePlayerStats(stats);
                }




        }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return true;
    }

}
