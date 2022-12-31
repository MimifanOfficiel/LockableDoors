package fr.mimifan.keydoors;

import fr.mimifan.keydoors.commands.CommandCheckDoor;
import fr.mimifan.keydoors.commands.CommandCreateKey;
import fr.mimifan.keydoors.commands.CommandRemoveKey;
import fr.mimifan.keydoors.events.EventJoin;
import fr.mimifan.keydoors.events.KeyHandler;
import fr.mimifan.keydoors.fileManager.createFiles;
import fr.mimifan.keydoors.fileManager.doorChecker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class KeyDoors extends JavaPlugin {

    private static KeyDoors instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getLogger().info("Demarrage du plugin de clefs pour NarutoRP");
        getCommand("createkey").setExecutor(new CommandCreateKey());
        getCommand("removekey").setExecutor(new CommandRemoveKey());
        getCommand("checkdoor").setExecutor(new CommandCheckDoor());
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EventJoin(), this);
        pm.registerEvents(new KeyHandler(this), this);
        createFiles.createFolder();
    }

    public static KeyDoors getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        getLogger().info("Le plugin de clés pour NarutoRP s'éteint.");
    }
}
