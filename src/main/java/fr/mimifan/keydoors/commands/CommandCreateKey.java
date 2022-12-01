package fr.mimifan.keydoors.commands;

import fr.mimifan.keydoors.KeyDoors;
import fr.mimifan.keydoors.enums.doors;
import fr.mimifan.keydoors.events.KeyHandler;
import fr.mimifan.keydoors.fileManager.addDoor;
import fr.mimifan.keydoors.utils.getTargetBlock;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandCreateKey implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(!(sender instanceof Player)) throw new CommandException("Vous ne pouvez pas exécuter cette commande depuis la console du serveur.");
        else {
            Player player = (Player) sender;
            if (args.length != 0)
                player.sendMessage("§cCette commande ne prend aucun argument. \n" + "§cFaites /createkey en regardant une porte.");
            else {
                Block targetedBlock = getTargetBlock.getTargetBlock(player, 5);
                if(doors.exists(targetedBlock.getType().name())){
                    addDoor.addDoor(player.getUniqueId(), targetedBlock.getLocation());
                    KeyHandler.giveKeys(player);
                    player.sendMessage("§aCette porte est maintenant fermée à clé !");
                } else {
                    player.sendMessage("§cVous devez viser une porte pour utiliser cette commande.");
                }
            }
        }
        return false;
    }
}
