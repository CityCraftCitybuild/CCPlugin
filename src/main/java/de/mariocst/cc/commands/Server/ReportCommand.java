package de.mariocst.cc.commands.Server;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.DiscordConfigData;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.geysermc.floodgate.api.FloodgateApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ReportCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            CCPlugin.getInstance().log("Du brauchst doch niemanden zu reporten. Ban ihn einfach!");
            return true;
        }

        if (player.hasPermission("mario.report") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
                CCPlugin.getInstance().reportForm.openReport(player);
                return true;
            }

            try {
                if (args.length >= 2) {
                    try {
                        Player t = player.getServer().getPlayer(args[0]);

                        OfflinePlayer oT = null;

                        for (OfflinePlayer offlinePlayer : CCPlugin.getInstance().getServer().getOfflinePlayers()) {
                            if (Objects.equals(offlinePlayer.getName(), args[0])) {
                                oT = offlinePlayer;
                            }
                        }

                        if (t != null) {
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

                            player.sendMessage(CCPlugin.getPrefix() + "Du hast den Spieler §a" + t.getName() + " §ffür §a" + msg + "§ferfolgreich reportet!");

                            if (staffOnline == 0)  player.sendMessage(CCPlugin.getPrefix() + "§cEs ist kein Teammitglied Online!");

                            if (!DiscordConfigData.getDiscordConfigData().getUrl().equals("")) {
                                try {
                                    CCPlugin.getInstance().sendReport(player, t, msg.toString());
                                    player.sendMessage(CCPlugin.getPrefix() + "Dein Report wurde erfolgreich an das Discord Team gesendet!");
                                }
                                catch (IOException e) {
                                    player.sendMessage(CCPlugin.getPrefix() + "Dein Report konnte leider nicht an das Discord Team gesendet werden, weil ein Fehler aufgetreten ist.");
                                }
                            }
                        }
                        else if (oT != null) {
                            StringBuilder msg = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {
                                msg.append(args[i]).append(" ");
                            }

                            int staffOnline = 0;

                            for (Player staff : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                                if (staff.hasPermission("mario.staff") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                                    staffOnline++;

                                    staff.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + player.getName() + " hat §a" + oT.getName() + " §ffür §a" + msg + "§freportet!");
                                }
                            }

                            player.sendMessage(CCPlugin.getPrefix() + "Du hast den Spieler §a" + oT.getName() + " §ffür §a" + msg + "§ferfolgreich reportet!");

                            if (staffOnline == 0)  player.sendMessage(CCPlugin.getPrefix() + "§cEs ist kein Teammitglied Online!");

                            if (!DiscordConfigData.getDiscordConfigData().getUrl().equals("")) {
                                try {
                                    CCPlugin.getInstance().sendReport(player, oT, msg.toString());
                                    player.sendMessage(CCPlugin.getPrefix() + "Dein Report wurde erfolgreich an das Discord Team gesendet!");
                                }
                                catch (IOException e) {
                                    player.sendMessage(CCPlugin.getPrefix() + "Dein Report konnte leider nicht an das Discord Team gesendet werden, weil ein Fehler aufgetreten ist.");
                                }
                            }
                        }
                        else {
                            player.sendMessage("§cDieser Spieler existiert nicht!");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                        }
                    }
                    catch (NullPointerException e) {
                        
                        player.sendMessage("§cDieser Spieler existiert nicht!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    }
                }
                else {
                    player.sendMessage("§cUsage: §e/report <Spieler> <Message>");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage("§cUsage: §e/report <Spieler> <Message>");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            final List<String> names = new ArrayList<>();

            for (Player player : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                names.add(player.getName());
            }

            for (OfflinePlayer offlinePlayer : CCPlugin.getInstance().getServer().getOfflinePlayers()) {
                names.add(offlinePlayer.getName());
            }

            StringUtil.copyPartialMatches(args[0], names, completions);
            Collections.sort(completions);
        }
        return completions;
    }
}
