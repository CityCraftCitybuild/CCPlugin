package de.mariocst.cc.commands.Inventory;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.backpack.BackpackLarge;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BackpackLargeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(CCPlugin.getPrefix() + "Dieser Command geht nur InGame!");
            return true;
        }

        if (player.hasPermission("mario.backpacklarge") || player.hasPermission("mario.*") || player.isOp()) {
            if (!player.getWorld().getName().equalsIgnoreCase("ffa") || player.isOp()) {
                BackpackLarge backpackLarge = CCPlugin.getInstance().getBackpackManagerLarge().getBackpackLarge(player.getUniqueId());
                player.openInventory(backpackLarge.getInventory());
            }
            else {
                player.sendMessage(CCPlugin.getPrefix() + "Du darfst dir keine Items in FFA cheaten :)");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
            }
        }
        else {
            sender.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }
}
