package fr.mimifan.keydoors.fileManager;

import fr.mimifan.keydoors.KeyDoors;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.UUID;

public class createFiles {

    public static void createFolder(){
        File folder = new File(KeyDoors.getInstance().getDataFolder() + "/PlayerDoors");
        if(!folder.exists()) folder.mkdir();
    }

    public static void createFile(UUID uuid){
        createFolder();
        File userFile = new File(KeyDoors.getInstance().getDataFolder() + "/PlayerDoors/", Bukkit.getPlayer(uuid).getName() + " " + uuid + ".yml");
        if(!userFile.exists()) try { userFile.createNewFile(); } catch (IOException e) {throw new RuntimeException(e);}
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(userFile);
        cfg.set("Username", Bukkit.getPlayer(uuid).getName());
        cfg.set("Total Doors", 0);
        try {cfg.save(userFile);} catch (IOException e) {throw new RuntimeException(e);}

    }

}
