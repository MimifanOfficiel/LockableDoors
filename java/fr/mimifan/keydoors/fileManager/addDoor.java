package fr.mimifan.keydoors.fileManager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

public class addDoor {

    public static void addDoor(UUID uuid, Location doorLocation){
        File file = filesUtils.getUserFile(uuid);
        YamlConfiguration cfg = filesUtils.getConfiguration(file);
        int nbDoors = cfg.getInt("Total Doors");
        cfg.set("Username", Bukkit.getPlayer(uuid).getName());
        cfg.set("KeyName","§eClé de la porte de " + Bukkit.getPlayer(uuid).getName() + ".");
        nbDoors++;
        cfg.set("Total Doors", nbDoors);
        cfg.set("Door " + nbDoors + ".Locked", true);
        cfg.set("Door " + nbDoors + ".Location" + ".X", doorLocation.getX());
        cfg.set("Door " + nbDoors + ".Location" + ".Y", doorLocation.getY());
        cfg.set("Door " + nbDoors + ".Location" + ".Z", doorLocation.getZ());

        filesUtils.saveFile(cfg, file);
    }

}
