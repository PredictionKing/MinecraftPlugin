package com.chipscrash.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterUserCommand implements CommandExecutor {

    private Connection con;

    public RegisterUserCommand(Connection con) {
        this.con = con;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(strings.length == 2){
                try {
                    PreparedStatement prep = con.prepareStatement("Select * from user where username='"+strings[0]+"' AND userpassword='"+strings[1]+"'");
                    if(!prep.executeQuery().next()){
                    prep= con.prepareStatement("INSERT INTO user (username, userpassword) VALUES ('"+ strings[0] +"','"+ strings[1] +"') ");
                    prep.executeUpdate();
                    player.sendMessage("§cUser created");
                    System.out.println("User created");}
                    else {
                        player.sendMessage("User already exists");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                player.sendMessage("§cBitte benutze §6/register <Username> <Password> §c!");
            }


        }

        return true;
    }
}
