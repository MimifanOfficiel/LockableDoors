package fr.mimifan.keydoors.fileManager;

import org.bukkit.configuration.file.YamlConfiguration;

import java.util.UUID;

public class getFileData {

    public static String getString(UUID uuid, String path){
        return (String) YamlConfiguration.loadConfiguration(filesUtils.getUserFile(uuid)).get(path);
    }
    public static int getInt(UUID uuid, String path){
        return (int) YamlConfiguration.loadConfiguration(filesUtils.getUserFile(uuid)).get(path);
    }
    public static double getDouble(UUID uuid, String path){
        return (double) YamlConfiguration.loadConfiguration(filesUtils.getUserFile(uuid)).get(path);
    }
    public static boolean getBoolean(UUID uuid, String path){
        return (boolean) YamlConfiguration.loadConfiguration(filesUtils.getUserFile(uuid)).get(path);
    }

}
