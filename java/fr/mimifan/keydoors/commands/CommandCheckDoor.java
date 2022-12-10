package fr.mimifan.keydoors.commands;

import fr.mimifan.keydoors.enums.doors;
import fr.mimifan.keydoors.fileManager.doorChecker;
import fr.mimifan.keydoors.utils.getTargetBlock;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandCheckDoor implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, String label, String[] args) {
        if(!(sender instanceof Player)) sender.sendMessage("Vous ne pouvez pas executer cette commande depuis la console.");
        else {
            Player player = (Player) sender;
            if (args.length != 0)
                player.sendMessage("§cCette commande ne prend aucun argument. \n" + "§cFaites /createkey en regardant une porte.");
            else {
                Block targetedBlock = getTargetBlock.getTargetBlock(player, 5);
                if(!doors.exists(targetedBlock.getType().name())) player.sendMessage("§cVous devez viser une porte pour effectuer cette commande.");
                else if(!doorChecker.isDoorLocked(targetedBlock.getLocation())) player.sendMessage("§aCette porte n'est pas verrouillée");
                else {
                    player.sendMessage("Cette porte est verrouilée par §b" + doorChecker.getDoorOwner(targetedBlock.getLocation()));
                }
            }
        }
        return false;
    }
}
