package de.mariocst.cc.commands.Player;

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

public class SpeedCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            CCPlugin.getInstance().log("Dieser Befehl geht nur InGame!");
            return true;
        }

        if (player.hasPermission("mario.speed") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length == 1) {
                    switch (args[0].toLowerCase()) {
                        case "fly" -> player.sendMessage(CCPlugin.getPrefix() + "Fly Speed: " + player.getFlySpeed());
                        case "walk" -> player.sendMessage(CCPlugin.getPrefix() + "Walk Speed: " + player.getWalkSpeed());
                        default -> {
                            sendUsage(sender);
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                        }
                    }
                }
                else if (args.length == 2) {
                    try {
                        float value = Float.parseFloat(args[1]);

                        switch (args[0].toLowerCase()) {
                            case "fly" -> {
                                if (value > 1) {
                                    player.sendMessage(CCPlugin.getPrefix() + "Bitte benutze eine kleinere Zahl!");
                                }
                                else if (value < -1) {
                                    player.sendMessage(CCPlugin.getPrefix() + "Bitte benutze eine größere Zahl!");
                                }
                                else {
                                    player.setFlySpeed(value);
                                    player.sendMessage(CCPlugin.getPrefix() + "Dein Fly Speed ist nun: " + value);
                                }
                            }
                            case "walk" -> {
                                if (value > 1) {
                                    player.sendMessage(CCPlugin.getPrefix() + "Bitte benutze eine kleinere Zahl!");
                                }
                                else if (value < -1) {
                                    player.sendMessage(CCPlugin.getPrefix() + "Bitte benutze eine größere Zahl!");
                                }
                                else {
                                    player.setWalkSpeed(value);
                                    player.sendMessage(CCPlugin.getPrefix() + "Dein Walk Speed ist nun: " + value);
                                }
                            }
                            default -> {
                                sendUsage(player);
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                            }
                        }
                    }
                    catch (NumberFormatException e) {
                        player.sendMessage(CCPlugin.getPrefix() + "Bitte gib eine gültige Zahl ein!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                    }
                }
                else {
                    sendUsage(player);
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sendUsage(player);
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "§cKeine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
        }
        return false;
    }

    public void sendUsage(CommandSender sender) {
        sender.sendMessage(CCPlugin.getPrefix() + "/speed fly|walk <Value>");
    }

    private final String[] MODES = { "fly", "walk" };

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
