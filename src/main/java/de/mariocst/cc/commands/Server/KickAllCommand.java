package de.mariocst.cc.commands.Server;

import de.mariocst.cc.CCPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class KickAllCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        final String usage = "/kickall [Grund]";

        if(!(sender instanceof Player player)) {
            try {
                int count = CCPlugin.getInstance().getServer().getOnlinePlayers().size();
                if (args.length == 0) {
                    if (count == 0) {
                        sender.sendMessage(CCPlugin.getPrefix() + "Kein Spieler ist Online lol");
                        return false;
                    }
                    else {
                        for (Player t : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                            if (!t.isOp() && !t.hasPermission("mario.*") && !t.hasPermission("*")) {
                                t.kick(Component.text("Kicked by Admin"));
                            }
                        }

                        sender.sendMessage(CCPlugin.getPrefix() + "Alle Spieler gekickt!");
                    }
                }
                else {
                    if (count == 0) {
                        sender.sendMessage(CCPlugin.getPrefix() + "Kein Spieler ist Online lol");
                        return false;
                    }
                    else {
                        StringBuilder reason = new StringBuilder();
                        for (String arg : args) {
                            reason.append(arg).append(" ");
                        }

                        for (Player t : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                            if (!t.isOp() && !t.hasPermission("mario.*") && !t.hasPermission("*")) {
                                t.kick(Component.text(reason.toString()));
                            }
                        }
                        sender.sendMessage(CCPlugin.getPrefix() + "Alle Spieler mit dem Grund " + reason + " gekickt!");
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(CCPlugin.getPrefix() + usage);
            }
            return false;
        }

        if(player.hasPermission("mario.kickall") || player.hasPermission("*") || player.isOp()) {
            try {
                int count = CCPlugin.getInstance().getServer().getOnlinePlayers().size();
                if (args.length == 0) {
                    if (count == 1) {
                        sender.sendMessage(CCPlugin.getPrefix() + "Kein Spieler ist Online lol");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                        return false;
                    }
                    else {
                        for (Player t : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                            if (t != sender && !t.isOp() && !t.hasPermission("mario.*") && !t.hasPermission("*")) {
                                t.kick(Component.text("Kicked by Admin"));
                            }
                        }

                        sender.sendMessage(CCPlugin.getPrefix() + "Alle Spieler gekickt!");
                    }
                }
                else {
                    if (count == 1) {
                        sender.sendMessage(CCPlugin.getPrefix() + "Kein Spieler ist Online lol");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                        return false;
                    }
                    else {
                        StringBuilder reason = new StringBuilder();
                        for (String arg : args) {
                            reason.append(arg).append(" ");
                        }

                        for (Player t : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                            if (t != sender && !t.isOp() && !t.hasPermission("mario.*") && !t.hasPermission("*")) {
                                t.kick(Component.text(reason.toString()));
                            }
                        }
                        sender.sendMessage(CCPlugin.getPrefix() + "Alle Spieler mit dem Grund " + reason + " gekickt!");
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(CCPlugin.getPrefix() + usage);
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
            }
        } else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }
}
