package de.mariocst.cc.commands.World;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetEntitesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            CCPlugin.getInstance().log("Dieser Command kann leider nur InGame ausgeführt werden!");
            return true;
        }

        if (player.hasPermission("mario.getentities") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            boolean entityFound = false;

            for (Entity entity : player.getWorld().getEntities()) {
                if (entity != player && !(entity instanceof ItemFrame)) {
                    entityFound = true;
                    player.sendMessage(CCPlugin.getPrefix() + entity.getName());
                }
            }

            if (!entityFound) player.sendMessage(CCPlugin.getPrefix() + "Keine Entities gefunden!");
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return true;
    }
}
