package de.mariocst.cc.commands.Chat;

import de.mariocst.cc.CCPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChatClearCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            for (Player cleared : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                if (!cleared.hasPermission("mario.chatclear.bypass") && !cleared.hasPermission("mario.*") && !cleared.hasPermission("*") && !cleared.isOp()) {
                    for (int i = 0; i <= 200; i++) {
                        cleared.sendMessage(Component.text("   "));
                    }
                }
            }

            CCPlugin.getInstance().getServer().broadcast(Component.text(CCPlugin.getPrefix() + "Die Konsole hat den Chat gecleart!"));
            return false;
        }

        if (player.hasPermission("mario.chatclear") || player.hasPermission("*") || player.isOp()) {
            for (Player cleared : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                if (!cleared.hasPermission("mario.chatclear.bypass") && !cleared.hasPermission("mario.*") && !cleared.hasPermission("*") && !cleared.isOp()) {
                    for (int i = 0; i <= 200; i++) {
                        cleared.sendMessage(Component.text("   "));
                    }
                }
            }

            TextComponent disName = (TextComponent) player.displayName();

            CCPlugin.getInstance().getServer().broadcast(Component.text(CCPlugin.getPrefix() + disName.content() + " hat den Chat gecleart!"));
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }
}
