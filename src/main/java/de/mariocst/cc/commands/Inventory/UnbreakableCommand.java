package de.mariocst.cc.commands.Inventory;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class UnbreakableCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(CCPlugin.getPrefix() + "Bitte führe den Command InGame aus!");
            return true;
        }

        if (player.hasPermission("mario.unbreakable") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
                ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();

                meta.setUnbreakable(!meta.isUnbreakable());

                player.getInventory().getItemInMainHand().setItemMeta(meta);

                player.sendMessage(meta.isUnbreakable() ? CCPlugin.getPrefix() + "Das Item ist nun unzerstörbar!" : "Das Item ist nun zerstörbar!");
            }
            else {
                sender.sendMessage(CCPlugin.getPrefix() + "Bitte halte ein Item in der Hand!");
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
