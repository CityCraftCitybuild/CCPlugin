package de.mariocst.cc.commands.Storing;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.backpack.BackpackStored;
import de.mariocst.cc.config.configdata.InventoryData;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FFAInventoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(CCPlugin.getPrefix() + "Dieser Command geht nur InGame!");
            return true;
        } else {
            Player player = (Player) sender;

            if(player.hasPermission("mario.ffainventory") || player.hasPermission("mario.*") || player.isOp()) {
                try {
                    if (args.length == 1) {
                        Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                        try {
                            if (t != null) {
                                InventoryData inventoryData = CCPlugin.getInstance().getInventoryDataManager().getInventory(t.getUniqueId());

                                player.openInventory(inventoryData.getInventory());
                            }
                        }
                        catch (NullPointerException e) {
                            e.printStackTrace();
                            player.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                        }
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    player.sendMessage(CCPlugin.getPrefix() + "/ffainventory <Spieler>");
                }
            }
            else {
                sender.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
            }
        }

        return false;
    }
}
