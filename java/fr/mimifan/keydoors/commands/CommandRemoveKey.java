package fr.mimifan.keydoors.commands;

import fr.mimifan.keydoors.enums.doors;
import fr.mimifan.keydoors.fileManager.doorChecker;
import fr.mimifan.keydoors.fileManager.removeDoor;
import fr.mimifan.keydoors.utils.getTargetBlock;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class CommandRemoveKey implements CommandExecutor {
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
                    if(doorChecker.getDoorOwner(targetedBlock.getLocation()).equalsIgnoreCase(player.getName())){
                        boolean hasItem = false;
                        for(ItemStack item : player.getInventory().getContents()){
                            if(item == null) continue;
                            if(doorChecker.isKeyValid(item, targetedBlock.getLocation())){
                                if(item.getAmount() != 3){
                                    player.sendMessage("§cVous devez avoir les 3 clés dans votre inventaire.");
                                    hasItem = true;
                                    continue;
                                }
                                removeDoor.removeDoor(player.getUniqueId(), targetedBlock.getLocation());
                                player.sendMessage("§aCette porte est maintenant déverrouillée.");
                                player.getInventory().remove(item);
                                hasItem = true;
                            }
                        }
                        if(!hasItem) player.sendMessage("§cVous devez avoir la clé dans votre inventaire.");

                    } else {
                        player.sendMessage("§cCette porte ne vous appartient pas.");
                    }
                }
            }
        }
        return false;
    }
}
