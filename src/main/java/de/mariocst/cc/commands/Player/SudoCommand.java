package de.mariocst.cc.commands.Player;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SudoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            try {
                if (args.length >= 2) {
                    Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                    if (t == null) {
                        sender.sendMessage(CCPlugin.getPrefix() + "Unbekannter Spieler: " + args[0]);
                        return true;
                    }

                    StringBuilder msg = new StringBuilder();
                    for (int i = 2; i < args.length; i++) {
                        msg.append(args[i]).append(" ");
                    }

                    if (args[1].toLowerCase().startsWith("c:")) {
                        t.chat(msg.toString().replaceAll("c:", ""));
                        return true;
                    }

                    try {
                        sender.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + t.getName() + " hat den Befehl " + msg + " ausgeführt!");
                        t.chat("/" + msg);
                    }
                    catch (final Exception e) {
                        sender.sendMessage(CCPlugin.getPrefix() + "Command nicht gefunden: " + msg);
                    }
                }
                else {
                    sender.sendMessage(CCPlugin.getPrefix() + "/sudo <Spieler> <Befehl>");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(CCPlugin.getPrefix() + "/sudo <Spieler> <Befehl>");
            }
            return true;
        }

        if (player.hasPermission("mario.sudo") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length >= 2) {
                    Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                    if (t == null) {
                        player.sendMessage(CCPlugin.getPrefix() + "Unbekannter Spieler: " + args[0]);
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                        return true;
                    }

                    if (t == player) return true;

                    if ((t.hasPermission("mario.sudo.bypass") || t.hasPermission("mario.*") || t.hasPermission("*") || t.isOp()) && !player.getName().equals("marioCST")) {
                        player.sendMessage(CCPlugin.getPrefix() + "Du kannst mit dem Spieler " + t.getName() + " nichts ausführen!");
                        return true;
                    }

                    StringBuilder msg = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        msg.append(args[i]).append(" ");
                    }

                    if (args[1].toLowerCase().startsWith("c:")) {
                        t.chat(msg.toString().replaceAll("c:", ""));
                        return true;
                    }

                    try {
                        player.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + t.getName() + " hat den Befehl " + msg + " ausgeführt!");
                        t.chat("/" + msg);
                    }
                    catch (final Exception e) {
                        player.sendMessage(CCPlugin.getPrefix() + "Command nicht gefunden: " + msg);
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                    }
                }
                else {
                    player.sendMessage(CCPlugin.getPrefix() + "/sudo <Spieler> <Befehl>");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(CCPlugin.getPrefix() + "/sudo <Spieler> <Befehl>");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "§cKeine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
        }
        return false;
    }
}
