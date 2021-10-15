package de.mariocst.cc.commands.Server;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StaffChatCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            if (args.length >= 1) {
                StringBuilder msg = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    msg.append(args[i]).append(" ");
                }

                CCPlugin.getInstance().log(CCPlugin.getInstance().getStaffChatPrefix().getConsolePrefix() + msg.toString().replaceAll("&", "§"));

                for (Player staff : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                    if (staff.hasPermission("mario.staff") || staff.hasPermission("mario.*") || staff.hasPermission("*") || staff.isOp()) {
                        staff.sendMessage(CCPlugin.getInstance().getStaffChatPrefix().getConsolePrefix() + msg.toString().replaceAll("&", "§"));
                    }
                }
            }
            else {
                sender.sendMessage(CCPlugin.getPrefix() + "/staffchat <Nachricht>");
            }
            return true;
        }

        if (player.hasPermission("mario.staff") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            if (args.length == 0) {
                player.sendMessage(CCPlugin.getPrefix() + "§6-- StaffChat Help --");
                player.sendMessage(CCPlugin.getPrefix() + "§6/sc help: Zeigt dir diese Hilfe an!");
                player.sendMessage(CCPlugin.getPrefix() + "§6/sc toggle: Schalte den StaffChat Modus ein oder aus! Kommt drauf an, ob es schon an oder aus war.");
                player.sendMessage(CCPlugin.getPrefix() + "§6/sc on: Schalte den StaffChat Modus ein!");
                player.sendMessage(CCPlugin.getPrefix() + "§6/sc off: Schalte den StaffChat Modus aus!");
                player.sendMessage(CCPlugin.getPrefix() + "§6/sc say <Text>: Schreibe einen beliebigen Text in den StaffChat!");
            }
            else {
                switch (args[0].toLowerCase()){
                    case "toggle" -> {
                        if (CCPlugin.getInstance().staffChat.contains(player)) {
                            CCPlugin.getInstance().staffChat.remove(player);

                            player.sendMessage(CCPlugin.getPrefix() + "Du schreibst nun nicht mehr in den Staff Chat!");
                        }
                        else {
                            CCPlugin.getInstance().staffChat.add(player);

                            player.sendMessage(CCPlugin.getPrefix() + "Du schreibst nun in den Staff Chat!");
                        }
                    }
                    case "on" -> {
                        CCPlugin.getInstance().staffChat.add(player);

                        player.sendMessage(CCPlugin.getPrefix() + "Du schreibst nun in den Staff Chat!");
                    }
                    case "off" -> {
                        CCPlugin.getInstance().staffChat.remove(player);

                        player.sendMessage(CCPlugin.getPrefix() + "Du schreibst nun nicht mehr in den Staff Chat!");
                    }
                    case "say" -> {
                        StringBuilder msg = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            msg.append(args[i]).append(" ");
                        }

                        CCPlugin.getInstance().log(CCPlugin.getInstance().getStaffChatPrefix().getPlayerPrefix(player) + msg.toString().replaceAll("&", "§"));

                        for (Player staff : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                            if (staff.hasPermission("mario.staff") || staff.hasPermission("mario.*") || staff.hasPermission("*") || staff.isOp()) {
                                staff.sendMessage(CCPlugin.getInstance().getStaffChatPrefix().getPlayerPrefix(player) + msg.toString().replaceAll("&", "§"));
                            }
                        }
                    }
                    default -> {
                        player.sendMessage(CCPlugin.getPrefix() + "§6-- StaffChat Help --");
                        player.sendMessage(CCPlugin.getPrefix() + "§6/sc help: Zeigt dir diese Hilfe an!");
                        player.sendMessage(CCPlugin.getPrefix() + "§6/sc toggle: Schalte den StaffChat Modus ein oder aus! Kommt drauf an, ob es schon an oder aus war.");
                        player.sendMessage(CCPlugin.getPrefix() + "§6/sc on: Schalte den StaffChat Modus ein!");
                        player.sendMessage(CCPlugin.getPrefix() + "§6/sc off: Schalte den StaffChat Modus aus!");
                        player.sendMessage(CCPlugin.getPrefix() + "§6/sc say <Text>: Schreibe einen beliebigen Text in den StaffChat!");
                    }
                }
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return true;
    }

    private final String[] MODES = { "help", "toggle", "on", "off", "say" };

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], Arrays.asList(MODES), completions);
            Collections.sort(completions);
        }
        return completions;
    }
}
