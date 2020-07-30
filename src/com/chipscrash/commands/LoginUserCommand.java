package com.chipscrash.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginUserCommand implements CommandExecutor {

    Connection con;
    HashMap<String, String> UserLoggedIn = new HashMap<>();

    public LoginUserCommand(Connection con) {
        this.con = con;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(!UserLoggedIn.containsKey(player.getUniqueId().toString())){
                if(strings.length == 1) {
                    try {
                        PreparedStatement prep = con.prepareStatement("Select * from user WHERE uniqueid='"+player.getUniqueId()+"' AND userpassword='"+strings[0]+"'");
                        if(prep.executeQuery().next()){
                            UserLoggedIn.put(player.getUniqueId().toString(), strings[0]);
                            player.sendMessage("§a[Logged in]");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }else{
                player.sendMessage("§cAlready Logged in!");
            }
        }
        return true;
    }
}
