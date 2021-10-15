package de.mariocst.cc.commands.Player;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        final String usage = "/feed [Spieler]";

        if (!(sender instanceof Player player)) {
            try {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("@a")) {
                        for (Player t : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                            t.setHealth(20d);
                            t.setFoodLevel(20);
                            t.sendMessage(CCPlugin.getPrefix() + "Du wurdest gesättigt!");
                            sender.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + t.getName() + " wurde gesättigt!");
                            t.playSound(t.getLocation(), Sound.AMBIENT_CAVE, 0.2f, 1.2f);
                        }
                    }
                    else {
                        Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                        try {
                            if (t != null) {
                                t.setFoodLevel(20);
                                t.sendMessage(CCPlugin.getPrefix() + "Du wurdest gesättigt!");
                                sender.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + t.getName() + " wurde gesättigt!");
                                t.playSound(t.getLocation(), Sound.AMBIENT_CAVE, 0.2f, 1.2f);
                            }
                            else {
                                sender.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                            }
                        }
                        catch (NullPointerException e) {
                            
                            sender.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                        }
                    }
                }
                else {
                    sender.sendMessage(CCPlugin.getPrefix() + usage);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(CCPlugin.getPrefix() + usage);
            }
            return true;
        }

        if (player.hasPermission("mario.feed") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length == 0) {
                    player.setFoodLevel(20);
                    player.sendMessage(CCPlugin.getPrefix() + "Du wurdest gesättigt!");
                    player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 0.2f, 1.2f);
                }
                else if (args.length == 1 && (player.hasPermission("mario.feed.other") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp())) {
                    if (args[0].equalsIgnoreCase("@a")) {
                        for (Player t : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                            if (t != player) {
                                t.setFoodLevel(20);
                                t.sendMessage(CCPlugin.getPrefix() + "Du wurdest gesättigt!");
                                player.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + t.getName() + " wurde gesättigt!");
                                t.playSound(t.getLocation(), Sound.AMBIENT_CAVE, 0.2f, 1.2f);
                            }
                        }
                    }
                    else {
                        Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                        try {
                            if (t != null) {
                                t.setFoodLevel(20);
                                t.sendMessage(CCPlugin.getPrefix() + "Du wurdest gesättigt!");
                                player.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + t.getName() + " wurde gesättigt!");
                                t.playSound(t.getLocation(), Sound.AMBIENT_CAVE, 0.2f, 1.2f);
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
                }
                else {
                    player.sendMessage(CCPlugin.getPrefix() + usage);
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(CCPlugin.getPrefix() + usage);
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return true;
    }
}
