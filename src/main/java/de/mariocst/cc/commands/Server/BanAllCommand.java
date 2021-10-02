package de.mariocst.cc.commands.Server;

import de.mariocst.cc.CCPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Date;

public class BanAllCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        final String usage = "/banall [Grund]";

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
                                CCPlugin.getInstance().getServer().getBanList(BanList.Type.NAME).addBan(t.getName(), "Banned by Admin", Date.from(Instant.ofEpochSecond(7257600000L)), "Console");
                                t.kick(Component.text("Banned by Admin"));
                            }
                        }

                        sender.sendMessage(CCPlugin.getPrefix() + "Alle Spieler gebannt!");
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
                                CCPlugin.getInstance().getServer().getBanList(BanList.Type.NAME).addBan(t.getName(), reason.toString(), Date.from(Instant.ofEpochSecond(7257600000L)), "Console");
                                t.kick(Component.text(reason.toString()));
                            }
                        }
                        sender.sendMessage(CCPlugin.getPrefix() + "Alle Spieler mit dem Grund " + reason + " gebannt!");
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(CCPlugin.getPrefix() + usage);
            }
            return false;
        }

        if(player.hasPermission("mario.banall") || player.hasPermission("*") || player.isOp()) {
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
                                CCPlugin.getInstance().getServer().getBanList(BanList.Type.NAME).addBan(t.getName(), "Banned by Admin", Date.from(Instant.ofEpochSecond(7257600000L)), "Console");
                                t.kick(Component.text("Banned by Admin"));
                            }
                        }

                        sender.sendMessage(CCPlugin.getPrefix() + "Alle Spieler gebannt!");
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
                                CCPlugin.getInstance().getServer().getBanList(BanList.Type.NAME).addBan(t.getName(), reason.toString(), Date.from(Instant.ofEpochSecond(7257600000L)), "Console");
                                t.kick(Component.text(reason.toString()));
                            }
                        }
                        sender.sendMessage(CCPlugin.getPrefix() + "Alle Spieler mit dem Grund " + reason + " gebannt!");
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
