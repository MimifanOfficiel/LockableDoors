package fr.mimifan.keydoors.fileManager;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

public class removeDoor {

    public static void removeDoor(UUID uuid, Location doorLoc){
        File file = filesUtils.getUserFile(uuid);
        YamlConfiguration cfg = filesUtils.getConfiguration(file);
        int nbDoors = cfg.getInt("Total Doors");
        nbDoors--;
        cfg.set("Total Doors", nbDoors);
        int numDoor = doorChecker.getDoorNumber(uuid, doorLoc);
        cfg.set("Door " + numDoor + ".Location" + ".ChangedXTimes", null);
        cfg.set("Door " + numDoor + ".Location" + ".X" + doorLoc.getX(), null);
        cfg.set("Door " + numDoor + ".Location" + ".Y" + doorLoc.getY(), null);
        cfg.set("Door " + numDoor + ".Location" + ".Z" + doorLoc.getZ(), null);
        filesUtils.saveFile(cfg, file);
    }

}
