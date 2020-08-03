package com.chipscrash.main;

import com.chipscrash.commands.*;
import com.chipscrash.files.CustomConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import static com.chipscrash.commands.LoginUserCommand.UserLoggedIn;

public class Main extends JavaPlugin {



    @Override
    public void onDisable() {
        System.out.println("Closed");
    }

    @Override
    public void onEnable() {
        System.out.println("Eco plugin started!");
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        CustomConfig.setup();
        CustomConfig.getCustomFile().addDefault("mysqlhost", "jdbcstring");
        CustomConfig.getCustomFile().addDefault("mysqluser", "root");
        CustomConfig.getCustomFile().addDefault("mysqlpass", "pass");
        CustomConfig.getCustomFile().options().copyDefaults(true);
        CustomConfig.save();
        try {
            Connection con = DriverManager.getConnection(CustomConfig.getCustomFile().getString("mysqlhost"),CustomConfig.getCustomFile().getString("mysqluser"), CustomConfig.getCustomFile().getString("mysqlpass"));
            this.getCommand("register").setExecutor(new RegisterUserCommand(con));
            this.getCommand("login").setExecutor(new LoginUserCommand(con));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.getCommand("god").setExecutor(new GodCommand());
        this.getCommand("heal").setExecutor(new HealCommand());
        this.getCommand("feed").setExecutor(new FeedCommand());

    }

}
