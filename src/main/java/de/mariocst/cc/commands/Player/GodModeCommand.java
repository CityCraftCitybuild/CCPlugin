package de.mariocst.cc.commands.Player;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GodModeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            CCPlugin.getInstance().log("Bitte führe den Command InGame aus!");
            return true;
        }

        if (player.hasPermission("mario.godmode") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            if (CCPlugin.getInstance().godMode.contains(player)) {
                CCPlugin.getInstance().godMode.remove(player);

                player.sendMessage(CCPlugin.getPrefix() + "Du bist nun nicht mehr unbesiegbar!");
            }
            else {
                CCPlugin.getInstance().godMode.add(player);

                player.sendMessage(CCPlugin.getPrefix() + "Du bist nun unbesiegbar!");
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return true;
    }
}
