package de.mariocst.cc.commands.Chat;

import de.mariocst.cc.CCPlugin;
import net.kyori.adventure.text.Component;
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
            for (int i = 0; i <= 200; i++) {
                CCPlugin.getInstance().getServer().broadcast(Component.text("   "));
            }

            CCPlugin.getInstance().getServer().broadcast(Component.text(CCPlugin.getPrefix() + "Die Konsole hat den Chat gecleart!"));
            return false;
        }

        if (player.hasPermission("mario.chatclear") || player.hasPermission("*") || player.isOp()) {
            for (int i = 0; i <= 200; i++) {
                CCPlugin.getInstance().getServer().broadcast(Component.text("   "));
            }

            CCPlugin.getInstance().getServer().broadcast(Component.text(CCPlugin.getPrefix() + player.displayName() + " hat den Chat gecleart!"));
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }
}
