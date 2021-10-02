package de.mariocst.cc.commands.Player;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        final String usage = "/heal [Spieler]";

        if (!(sender instanceof Player player)) {
            try {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("@a")) {
                        for (Player t : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                            t.setHealth(20d);
                            t.sendMessage(CCPlugin.getPrefix() + "Du wurdest geheilt!");
                            sender.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + t.getName() + " wurde geheilt!");
                            t.playSound(t.getLocation(), Sound.AMBIENT_CAVE, 0.2f, 1.2f);
                        }
                    }
                    else {
                        Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                        try {
                            if (t != null) {
                                t.setHealth(20d);
                                t.sendMessage(CCPlugin.getPrefix() + "Du wurdest geheilt!");
                                sender.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + t.getName() + " wurde geheilt!");
                                t.playSound(t.getLocation(), Sound.AMBIENT_CAVE, 0.2f, 1.2f);
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

        if (player.hasPermission("mario.heal") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length == 0) {
                    player.setHealth(20d);
                    player.sendMessage(CCPlugin.getPrefix() + "Du wurdest geheilt!");
                    player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 0.2f, 1.2f);
                }
                else if (args.length == 1 && player.hasPermission("mario.heal.other")) {
                    if (args[0].equalsIgnoreCase("@a")) {
                        for (Player t : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                            if (t != player) {
                                t.setHealth(20d);
                                t.sendMessage(CCPlugin.getPrefix() + "Du wurdest geheilt!");
                                player.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + t.getName() + " wurde geheilt!");
                                t.playSound(t.getLocation(), Sound.AMBIENT_CAVE, 0.2f, 1.2f);
                            }
                        }
                    }
                    else {
                        Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                        try {
                            if (t != null) {
                                t.setHealth(20d);
                                t.sendMessage(CCPlugin.getPrefix() + "Du wurdest geheilt!");
                                player.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + t.getName() + " wurde geheilt!");
                                t.playSound(t.getLocation(), Sound.AMBIENT_CAVE, 0.2f, 1.2f);
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
                }
                else {
                    player.sendMessage(CCPlugin.getPrefix() + usage);
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(CCPlugin.getPrefix() + usage);
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return true;
    }
}
