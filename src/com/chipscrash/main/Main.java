package com.chipscrash.main;

import com.chipscrash.commands.HealCommand;
import com.chipscrash.commands.LoginUserCommand;
import com.chipscrash.commands.RegisterUserCommand;
import com.chipscrash.files.CustomConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        this.getCommand("heal").setExecutor(new HealCommand());

    }

}
