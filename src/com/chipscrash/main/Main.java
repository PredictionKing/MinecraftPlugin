package com.chipscrash.main;

import com.chipscrash.commands.HealCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {



    @Override
    public void onDisable() {
        System.out.println("Closed");
    }

    @Override
    public void onEnable() {
        System.out.println("Eco plugin started!");
        this.getCommand("heal").setExecutor(new HealCommand());
    }

}
