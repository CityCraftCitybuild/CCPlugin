package de.mariocst.cc.commands.Inventory;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClearInventoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            try {
                if (args.length == 1) {
                    try {
                        Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                        if (t != null) {
                            if (!t.getWorld().getName().equalsIgnoreCase("ffa")) {
                                t.getInventory().clear();
                                t.sendMessage(CCPlugin.getPrefix() + "Dein Inventar wurde gecleart!");
                                sender.sendMessage(CCPlugin.getPrefix() + "Das Inventar von " + t.getName() + " wurde gecleart!");
                            }
                            else {
                                sender.sendMessage(CCPlugin.getPrefix() + "Bitte nimm dem Spieler die Items von FFA nicht weg. Danke");
                            }
                        }
                        else {
                            sender.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                        }
                    }
                    catch (NullPointerException e) {
                        sender.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                    }
                }
                else {
                    sender.sendMessage(CCPlugin.getPrefix() + "/clearinventory <Spieler>");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(CCPlugin.getPrefix() + "/clearinventory <Spieler>");
            }
            return false;
        }

        if (player.hasPermission("mario.clear") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length == 0) {
                    player.getInventory().clear();
                    player.sendMessage(CCPlugin.getPrefix() + "Dein Inventar wurde gecleart!");
                }
                else if (args.length == 1) {
                    try {
                        Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                        if (t != null) {
                            if (!player.getWorld().getName().equalsIgnoreCase("ffa")) {
                                t.getInventory().clear();
                                t.sendMessage(CCPlugin.getPrefix() + "Dein Inventar wurde gecleart!");
                                player.sendMessage(CCPlugin.getPrefix() + "Das Inventar von " + t.getName() + " wurde gecleart!");
                            }
                            else {
                                player.sendMessage(CCPlugin.getPrefix() + "Bitte nimm dem Spieler die Items von FFA nicht weg. Danke");
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                            }
                        }
                        else {
                            player.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                        }
                    }
                    catch (NullPointerException e) {
                        player.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    }
                }
                else {
                    player.sendMessage(CCPlugin.getPrefix() + "/clearinventory [Spieler]");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(CCPlugin.getPrefix() + "/clearinventory [Spieler]");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }
}
