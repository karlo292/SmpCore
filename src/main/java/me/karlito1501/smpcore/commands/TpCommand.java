package me.karlito1501.smpcore.commands;

import me.karlito1501.smpcore.podels.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.karlito1501.smpcore.SmpCore.*;

public class TpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player player){
            if(!player.hasPermission("smpcore.tp")){
                player.sendMessage(NO_PERMISSION);
                return true;
            }
            if(args.length == 0){
                player.sendMessage(MESSAGE_PREFIX + " Usage: /tp <player>");
                return true;
            }
            Player playerTarget = Bukkit.getPlayer(args[0]);
            String playerTargetUsername = playerTarget.getDisplayName();
            if(playerTarget == null){
                player.sendMessage(MESSAGE_PREFIX_RED + " That player is not online!");
                return true;
            }
            Location targetLocation = playerTarget.getLocation();
            player.teleport(targetLocation);
            player.sendMessage(MESSAGE_PREFIX + " Teleported to " + playerTargetUsername);

        }

        return true;
    }
}
