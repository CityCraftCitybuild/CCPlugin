package de.mariocst.cc.commands.Chat;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatClearCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int schleife1 = 0;
        int schleife2 = 0;

        if(!(sender instanceof Player)) {
            while (schleife1 < 200) {
                schleife1++;
                Bukkit.broadcastMessage("   ");
            }
            Bukkit.broadcastMessage(CCPlugin.getPrefix() + "Die Konsole hat den Chat gecleart!");
            return false;
        }

        Player player = (Player) sender;
        if(player.hasPermission("mario.chatclear") || player.hasPermission("*") || player.isOp()) {
            while (schleife2 < 200) {
                schleife2++;
                Bukkit.broadcastMessage("   ");
            }
            Bukkit.broadcastMessage(CCPlugin.getPrefix() + player.getDisplayName() + " hat den Chat gecleart!");
        } else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }
}
