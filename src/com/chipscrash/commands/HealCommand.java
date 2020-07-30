package com.chipscrash.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.chipscrash.commands.LoginUserCommand.UserLoggedIn;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(UserLoggedIn.containsKey(player.getUniqueId().toString())) {
                if (strings.length == 0) {
                    player.setHealth(20);
                    player.setFoodLevel(20);
                    player.sendMessage("§aDu wurdest geheilt!");
                } else if (strings.length == 1) {
                    Player targetPlayer = Bukkit.getPlayer(strings[0]);
                    System.out.println(targetPlayer);
                    if (targetPlayer != null && targetPlayer != player) {
                        targetPlayer.setHealth(20);
                        targetPlayer.setFoodLevel(20);
                        player.sendMessage("§aDu hast " + targetPlayer.getName() + " geheilt!");
                        targetPlayer.sendMessage("§aDu wurdest von " + player.getName() + " geheilt!");
                    } else if (targetPlayer == player) {
                        player.setHealth(20);
                        player.setFoodLevel(20);
                        player.sendMessage("§aDu wurdest geheilt!");
                    }
                } else {
                    player.sendMessage("§cBitte benutze §6/heal §c!");
                }
            }else{
                player.sendMessage("§cPlease login to use this command");
            }

        }

        return true;
    }
}
