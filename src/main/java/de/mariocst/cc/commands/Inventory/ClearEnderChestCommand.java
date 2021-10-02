package de.mariocst.cc.commands.Inventory;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClearEnderChestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            try {
                if (args.length == 1) {
                    try {
                        Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                        if (t != null) {
                            t.getEnderChest().clear();
                            t.sendMessage(CCPlugin.getPrefix() + "Deine Ender Chest wurde gecleart!");
                            sender.sendMessage(CCPlugin.getPrefix() + "Die Ender Chest von " + t.getName() + " wurde gecleart!");
                        }
                        else {
                            sender.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        sender.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                    }
                }
                else {
                    sender.sendMessage(CCPlugin.getPrefix() + "/clearenderchest <Spieler>");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(CCPlugin.getPrefix() + "/clearenderchest <Spieler>");
            }
            return false;
        }

        if (player.hasPermission("mario.clear") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length == 0) {
                    player.getEnderChest().clear();
                    player.sendMessage(CCPlugin.getPrefix() + "Deine Ender Chest wurde gecleart!");
                }
                else if (args.length == 1) {
                    try {
                        Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                        if (t != null) {
                            t.getEnderChest().clear();
                            t.sendMessage(CCPlugin.getPrefix() + "Deine Ender Chest wurde gecleart!");
                            player.sendMessage(CCPlugin.getPrefix() + "Die Ender Chest von " + t.getName() + " wurde gecleart!");
                        }
                        else {
                            player.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        player.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                    }
                }
                else {
                    player.sendMessage(CCPlugin.getPrefix() + "/clearenderchest oder /clearenderchest <Spieler>");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(CCPlugin.getPrefix() + "/clearenderchest oder /clearenderchest <Spieler>");
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
