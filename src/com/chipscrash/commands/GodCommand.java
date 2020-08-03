package com.chipscrash.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.chipscrash.commands.LoginUserCommand.UserLoggedIn;

public class GodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (UserLoggedIn.containsKey(player.getUniqueId().toString())) {
                player.setInvulnerable(true);
                player.setFlySpeed((float)1);
                player.setAllowFlight(true);
                player.setWalkSpeed((float)1);
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 3));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 5));
                player.setGlowing(true);
                player.sendMessage("§aDu bist Gott!");
            }else{
                player.sendMessage("§cYou need to be logged in to become god");
            }
        }
        return true;
    }
}
