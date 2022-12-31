package fr.mimifan.keydoors.events;

import fr.mimifan.keydoors.KeyDoors;
import fr.mimifan.keydoors.enums.doors;
import fr.mimifan.keydoors.fileManager.doorChecker;
import fr.mimifan.keydoors.fileManager.getFileData;
import fr.mimifan.keydoors.fileManager.lockDoor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements Listener {

    private static KeyDoors main;
    public KeyHandler(KeyDoors main) {
        this.main = main;
    }

    public static void giveKeys(Player player){
        short nbKeys = (short) main.getConfig().getInt("doors.given_keys");
        ItemStack key = new ItemStack(Material.LEVER, nbKeys);
        ItemMeta kM = key.getItemMeta();
        kM.setDisplayName(getFileData.getString(player.getUniqueId(), "KeyName"));

        List<String> lore = new ArrayList<>();
        lore.add(String.valueOf(getFileData.getDouble(player.getUniqueId(), "Door " + getFileData.getInt(player.getUniqueId(), "Total Doors") + ".Location.X")));
        lore.add(String.valueOf(getFileData.getDouble(player.getUniqueId(), "Door " + getFileData.getInt(player.getUniqueId(), "Total Doors") + ".Location.Y")));
        lore.add(String.valueOf(getFileData.getDouble(player.getUniqueId(), "Door " + getFileData.getInt(player.getUniqueId(), "Total Doors") + ".Location.Z")));
        kM.setLore(lore);
        lore.clear();
        key.setItemMeta(kM);
        player.getInventory().addItem(key);
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(!(event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if(!doors.exists(event.getClickedBlock().getType().name())) return;
        boolean doorLocked = doorChecker.isDoorLocked(event.getClickedBlock().getLocation());
        if(doorChecker.isKeyValid(player.getInventory().getItemInMainHand(), event.getClickedBlock().getLocation())) {
            lockDoor.lockDoor(!doorLocked, event.getClickedBlock().getLocation());
            if(!doorLocked) return;
        } else {
            if(doorLocked) event.setCancelled(true);
        }
    }

}
