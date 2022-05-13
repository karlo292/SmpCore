package me.karlito1501.smpcore.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.karlito1501.smpcore.SmpCore.*;

public class GamemodeAdventureCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(player.hasPermission("smpcore.gamemode.adventure")){

                if(args.length > 0){
                    Player player1 = player.getServer().getPlayerExact(args[0]);
                    if(player1 != null) {

                        player1.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(MESSAGE_PREFIX + " Gamemode of " + args[0] + " changed to adventure");
                        return true;
                    }else{
                        player.sendMessage(MESSAGE_PREFIX_RED + " " + args[0] + " is not online!");
                        return true;
                    }
                }


                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage(MESSAGE_PREFIX +" Gamemode changed to adventure");
            }else{
                player.sendMessage(MESSAGE_PREFIX_RED + " " + NO_PERMISSION);
            }
        }



        return true;
    }
}
