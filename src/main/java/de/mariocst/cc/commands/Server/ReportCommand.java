package de.mariocst.cc.commands.Server;

import de.mariocst.cc.CCPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            CCPlugin.getInstance().log("Du brauchst doch niemanden zu reporten. Ban ihn einfach!");
            return true;
        }

        if (player.hasPermission("mario.report") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length >= 2) {
                    try {
                        Player t = player.getServer().getPlayer(args[0]);

                        if (t != null) {
                            if (t.getName().equals("marioCST")) {
                                player.kick(Component.text("§4Würd' mir stinken, wenn ich du wäre"));
                                return false;
                            }

                            StringBuilder msg = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {
                                msg.append(args[i]).append(" ");
                            }

                            int staffOnline = 0;

                            for (Player staff : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                                if (staff.hasPermission("mario.staff") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                                    staffOnline++;

                                    staff.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + player.getName() + " hat §a" + t.getName() + " §ffür §a" + msg + "§freportet!");
                                }
                            }

                            if (staffOnline > 0) {
                                player.sendMessage(CCPlugin.getPrefix() + "Du hast den Spieler §a" + t.getName() + " §ffür §a" + msg + "§ferfolgreich reportet!");
                            }
                            else {
                                player.sendMessage(CCPlugin.getPrefix() + "§cEs ist kein Teammitglied Online!");
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                            }
                        }
                        else {
                            player.sendMessage("§cDieser Spieler existiert nicht!");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        player.sendMessage("§cDieser Spieler existiert nicht!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                    }
                }
                else {
                    player.sendMessage("§cUsage: §e/report <Spieler> <Message>");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage("§cUsage: §e/report <Spieler> <Message>");
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
