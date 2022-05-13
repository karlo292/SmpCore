package me.karlito1501.smpcore.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.karlito1501.smpcore.SmpCore.*;

public class GamemodeSurvivalCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(player.hasPermission("smpcore.gamemode.survival")){
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(MESSAGE_PREFIX +" Gamemode changed to survival");
            }else{
                player.sendMessage(MESSAGE_PREFIX_RED + " " + NO_PERMISSION);
            }
        }


        return true;
    }

}
