package de.mariocst.cc.commands.Chat;

import de.mariocst.cc.CCPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            if (args.length >= 1) {
                StringBuilder msg = new StringBuilder();
                for (String arg : args) {
                    msg.append(arg).append(" ");
                }

                CCPlugin.getInstance().getServer().broadcast(Component.text(CCPlugin.getPrefix()));
                CCPlugin.getInstance().getServer().broadcast(Component.text(CCPlugin.getPrefix() + msg.toString().replaceAll("&", "§")));
                CCPlugin.getInstance().getServer().broadcast(Component.text(CCPlugin.getPrefix()));
            }
            else {
                sender.sendMessage("§cUsage: §e/broadcast <Message>");
            }
            return false;
        }

        if (player.hasPermission("mario.broadcast") || player.hasPermission("*") || player.isOp()) {
            if (args.length >= 1) {
                StringBuilder msg = new StringBuilder();
                for (String arg : args) {
                    msg.append(arg).append(" ");
                }

                CCPlugin.getInstance().getServer().broadcast(Component.text(CCPlugin.getPrefix()));
                CCPlugin.getInstance().getServer().broadcast(Component.text(CCPlugin.getPrefix() + msg.toString().replaceAll("&", "§")));
                CCPlugin.getInstance().getServer().broadcast(Component.text(CCPlugin.getPrefix()));
            }
            else {
                player.sendMessage(CCPlugin.getPrefix() + "§cUsage: §e/broadcast <Message>");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }
}
