package fr.mimifan.keydoors.fileManager;

import fr.mimifan.keydoors.KeyDoors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class doorChecker {


    public static boolean isDoorLocked(Location doorLoc){
        File folder = new File(KeyDoors.getInstance().getDataFolder() + "/PlayerDoors");
        //Bukkit.getScheduler().runTaskAsynchronously(KeyDoors.getPlugin(KeyDoors.class), new Runnable() {
            //@Override
            //public void run() {
                for(File file : folder.listFiles()){
                    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                    int nbDoors = cfg.getInt("Total Doors");
                    for(int i=1; i <= nbDoors; i++){
                        int x = cfg.getInt("Door " + i + ".Location" + ".X");
                        int y = cfg.getInt("Door " + i + ".Location" + ".Y");
                        int z = cfg.getInt("Door " + i + ".Location" + ".Z");
                        if(x==doorLoc.getX() && y==doorLoc.getY() && z==doorLoc.getZ())return true;
                        else {
                            continue;
                        }
                    }
                }
            //}
        //});
        return false;
    }

    public static boolean isKeyValid(ItemStack key, Location doorLoc){
        if(!(key.hasItemMeta() && key.getItemMeta().hasLore())) return false;
        List<String> coords = key.getItemMeta().getLore();
        if(Double.parseDouble(coords.get(0)) == doorLoc.getX() && Double.parseDouble(coords.get(1)) == doorLoc.getY() && Double.parseDouble(coords.get(2)) == doorLoc.getZ()){
            return true;
        } else {
            return false;
        }
    }

    public static String getDoorOwner(Location doorLoc){
        File folder = new File(KeyDoors.getInstance().getDataFolder() + "/PlayerDoors");
        for(File file : folder.listFiles()){
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            int nbDoors = cfg.getInt("Total Doors");
            for(int i=1; i <= nbDoors; i++){
                int x = cfg.getInt("Door " + i + ".Location" + ".X");
                int y = cfg.getInt("Door " + i + ".Location" + ".Y");
                int z = cfg.getInt("Door " + i + ".Location" + ".Z");
                if(x==doorLoc.getX() && y==doorLoc.getY() && z==doorLoc.getZ()){
                    return file.getName().split(" ")[0];
                } else {
                    continue;
                }
            }
        }
        return "Null";
    }

    public static int getDoorNumber(UUID uuid, Location doorLoc){
        File file = filesUtils.getUserFile(uuid);
        YamlConfiguration cfg = filesUtils.getConfiguration(file);
        int nbDoors = cfg.getInt("Total Doors");
        for(int i=1; i <= nbDoors; i++){
            int x = cfg.getInt("Door " + i + ".Location" + ".X");
            int y = cfg.getInt("Door " + i + ".Location" + ".Y");
            int z = cfg.getInt("Door " + i + ".Location" + ".Z");
            if(x==doorLoc.getX() && y==doorLoc.getY() && z==doorLoc.getZ()){
                return i;
            } else {
                continue;
            }
        }
        return -1;
    }

}
