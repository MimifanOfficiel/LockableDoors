package fr.mimifan.keydoors.fileManager;

import fr.mimifan.keydoors.KeyDoors;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class filesUtils {

    public static File getUserFile(UUID uuid){
        File userFile = new File(KeyDoors.getInstance().getDataFolder() + "/PlayerDoors/", Bukkit.getPlayer(uuid).getName() + " " + uuid + ".yml");
        return userFile;
    }

    public static YamlConfiguration getConfiguration(File file){
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg;
    }

    public static void saveFile(YamlConfiguration cfg, File file){
        try {
            cfg.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
