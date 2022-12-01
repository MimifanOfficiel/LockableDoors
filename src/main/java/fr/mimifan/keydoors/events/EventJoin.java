package fr.mimifan.keydoors.events;

import fr.mimifan.keydoors.fileManager.createFiles;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        createFiles.createFile(event.getPlayer().getUniqueId());
    }

}
