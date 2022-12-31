package fr.mimifan.keydoors.fileManager;

import fr.mimifan.keydoors.KeyDoors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class lockDoor {

    public static void lockDoor(boolean hasToBeLocked, Location doorLoc){
        File folder = new File(KeyDoors.getInstance().getDataFolder() + "/PlayerDoors");
        String doorOwner = doorChecker.getDoorOwner(doorLoc);
        for(File file : folder.listFiles()){
            if(!(file.getName().split(" ")[0] == doorOwner)) continue;
            File doorFile = new File(folder + doorOwner + " " + Bukkit.getPlayer(doorOwner).getUniqueId());
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(doorFile);
            int nbDoors = cfg.getInt("Total Doors");
            for(int i=1; i <= nbDoors; i++){
                int x = cfg.getInt("Door " + i + ".Location" + ".X");
                int y = cfg.getInt("Door " + i + ".Location" + ".Y");
                int z = cfg.getInt("Door " + i + ".Location" + ".Z");
                if(x==doorLoc.getX() && y==doorLoc.getY() && z==doorLoc.getZ()){
                    cfg.set("Door " + i + ".Locked", hasToBeLocked);
                } else {
                    continue;
                }
            }
        }
    }
}
