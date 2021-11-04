package de.mariocst.cc.commands.Admin;

import de.mariocst.cc.CCPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ScaffoldCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(CCPlugin.getPrefix() + "Bitte führe den Command InGame aus!");
            return true;
        }

        if (player.hasPermission("mario.scaffold") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            boolean foundBlock = false;

            for (ItemStack stack : player.getInventory().getContents()) {
                if (stack != null) {
                    if (stack.hasItemMeta()) {
                        if (Objects.equals(stack.getItemMeta().displayName(), Component.text("§cScaffold Wool"))) {
                            player.getInventory().remove(stack);
                            foundBlock = true;
                        }
                    }
                }
            }

            if (!foundBlock) {
                ItemStack wool = new ItemStack(Material.WHITE_WOOL, 1);
                ItemMeta woolMeta = wool.getItemMeta();

                woolMeta.displayName(Component.text("§cScaffold Wool"));
                wool.setItemMeta(woolMeta);

                player.getInventory().addItem(wool);

                player.sendMessage(CCPlugin.getPrefix() + "Dir wurde die Scaffold Wolle hinzugefügt!");
            }
            else {
                player.sendMessage(CCPlugin.getPrefix() + "Dir wurde die Scaffold Wolle entfernt!");
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }
}
