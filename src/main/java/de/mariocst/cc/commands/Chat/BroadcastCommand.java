package de.mariocst.cc.commands.Chat;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            if (args.length >= 1) {
                String msg = "";
                for(int i = 0; i < args.length; i++) {
                    msg = msg + args[i] + " ";
                }

                Bukkit.broadcastMessage(CCPlugin.getPrefix());
                Bukkit.broadcastMessage(CCPlugin.getPrefix() + msg.replaceAll("&", "§"));
                Bukkit.broadcastMessage(CCPlugin.getPrefix());
            } else {
                sender.sendMessage("§cUsage: §e/broadcast <Message>");
            }
            return false;
        }

        Player player = (Player) sender;
        if(player.hasPermission("mario.broadcast") || player.hasPermission("*") || player.isOp()) {
            if (args.length >= 1) {
                String msg = "";
                for(int i = 0; i < args.length; i++) {
                    msg = msg + args[i] + " ";
                }

                Bukkit.broadcastMessage(CCPlugin.getPrefix());
                Bukkit.broadcastMessage(CCPlugin.getPrefix() + msg.replaceAll("&", "§"));
                Bukkit.broadcastMessage(CCPlugin.getPrefix());
            } else {
                sender.sendMessage("§cUsage: §e/broadcast <Message>");
            }
        } else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }
}
