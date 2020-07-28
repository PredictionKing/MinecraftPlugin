package com.chipscrash.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig {

    private static File file;
    private static FileConfiguration customFile;


    //Finds or generates settings file
    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Eco").getDataFolder(), "settings.yml");

        if(!file.exists()){
            try {
                file.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);

    }

    public static FileConfiguration getCustomFile() {
        return customFile;
    }

    public static void save(){
        try {
            customFile.save(file);
        }catch (IOException e){
            System.out.println("Couldn't save File");
            e.printStackTrace();
        }
    }

    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
