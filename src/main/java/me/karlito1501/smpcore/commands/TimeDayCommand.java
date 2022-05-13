package me.karlito1501.smpcore.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.karlito1501.smpcore.SmpCore.MESSAGE_PREFIX;
import static me.karlito1501.smpcore.SmpCore.NO_PERMISSION;

public class TimeDayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player player){
            if(player.hasPermission("smpcore.time.day")){
                World world = player.getWorld();
                world.setTime(0);
                player.sendMessage(MESSAGE_PREFIX + " Changed time to day");
            }else{
                player.sendMessage(NO_PERMISSION);
            }
        }

        return true;
    }
}
