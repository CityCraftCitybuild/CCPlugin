package de.mariocst.cc.commands.Server;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.*;

public class PluginCommand implements CommandExecutor, TabCompleter {
    private final String[] LOADER = { "load", "unload", "reload" };

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            try {
                if (args.length == 2) {
                    if (args[1].equalsIgnoreCase("CCPlugin")) {
                        sender.sendMessage(CCPlugin.getPrefix() + "Du kannst diese Plugin aus technischen Gründen nicht entladen!");
                        return true;
                    }

                    switch (args[0].toLowerCase()) {
                        case "load" -> {
                            File dir = new File("./plugins");
                            File jar = new File(dir, args[1] + ".jar");

                            try {
                                try {
                                    CCPlugin.getInstance().getServer().getPluginManager().loadPlugin(jar);
                                    CCPlugin.getInstance().getServer().getPluginManager().enablePlugin(Objects.requireNonNull(CCPlugin.getInstance().getServer().getPluginManager().getPlugin(args[1])));

                                    sender.sendMessage(CCPlugin.getPrefix() + "Plugin " + args[1] + " geladen!");
                                }
                                catch (NullPointerException e) {
                                    sender.sendMessage(CCPlugin.getPrefix() + "Konnte Datei nicht finden: " + args[1] + ".jar");
                                }
                            }
                            catch (InvalidPluginException | InvalidDescriptionException e) {
                                sender.sendMessage(CCPlugin.getPrefix() + "Das Plugin ist entweder kein Plugin, oder hat eine falsche Beschreibung!");
                            }
                        }
                        case "unload" -> {
                            try {
                                CCPlugin.getInstance().getServer().getPluginManager().disablePlugin(Objects.requireNonNull(CCPlugin.getInstance().getServer().getPluginManager().getPlugin(args[1])));

                                sender.sendMessage(CCPlugin.getPrefix() + "Plugin " + args[1] + " entladen!");
                            }
                            catch (NullPointerException e) {
                                sender.sendMessage(CCPlugin.getPrefix() + "Das Plugin " + args[1] + " existiert nicht!");
                            }
                        }
                        case "reload" -> {
                            try {
                                CCPlugin.getInstance().getServer().getPluginManager().disablePlugin(Objects.requireNonNull(CCPlugin.getInstance().getServer().getPluginManager().getPlugin(args[1])));
                            }
                            catch (NullPointerException e) {
                                sender.sendMessage(CCPlugin.getPrefix() + "Das Plugin " + args[1] + " existiert nicht!");
                                return true;
                            }

                            File dir = new File("./plugins");
                            File jar = new File(dir, args[1] + ".jar");

                            try {
                                try {
                                    CCPlugin.getInstance().getServer().getPluginManager().loadPlugin(jar);
                                    CCPlugin.getInstance().getServer().getPluginManager().enablePlugin(Objects.requireNonNull(CCPlugin.getInstance().getServer().getPluginManager().getPlugin(args[1])));
                                }
                                catch (NullPointerException e) {
                                    sender.sendMessage(CCPlugin.getPrefix() + "Konnte Datei nicht finden: " + args[1] + ".jar");
                                    return true;
                                }
                            }
                            catch (InvalidPluginException | InvalidDescriptionException e) {
                                sender.sendMessage(CCPlugin.getPrefix() + "Das Plugin ist entweder kein Plugin, oder hat eine falsche Beschreibung!");
                                return true;
                            }

                            sender.sendMessage(CCPlugin.getPrefix() + "Plugin " + args[1] + " neu geladen!");
                        }
                        default -> sender.sendMessage(CCPlugin.getPrefix() + "/plugin <load|unload|reload> <Name>");
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(CCPlugin.getPrefix() + "/plugin <load|unload|reload> <Name>");
            }
            return true;
        }

        if (player.hasPermission("mario.plugin") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length == 2) {
                    if (args[1].equalsIgnoreCase("CCPlugin")) {
                        sender.sendMessage(CCPlugin.getPrefix() + "Du kannst diese Plugin aus technischen Gründen nicht entladen!");
                        return true;
                    }

                    switch (args[0].toLowerCase()) {
                        case "load" -> {
                            File dir = new File("./plugins");
                            File jar = new File(dir, args[1] + ".jar");

                            try {
                                try {
                                    CCPlugin.getInstance().getServer().getPluginManager().loadPlugin(jar);
                                    CCPlugin.getInstance().getServer().getPluginManager().enablePlugin(Objects.requireNonNull(CCPlugin.getInstance().getServer().getPluginManager().getPlugin(args[1])));

                                    sender.sendMessage(CCPlugin.getPrefix() + "Plugin " + args[1] + " geladen!");
                                }
                                catch (NullPointerException e) {
                                    sender.sendMessage(CCPlugin.getPrefix() + "Konnte Datei nicht finden: " + args[1] + ".jar");
                                }
                            }
                            catch (InvalidPluginException | InvalidDescriptionException e) {
                                sender.sendMessage(CCPlugin.getPrefix() + "Das Plugin ist entweder kein Plugin, oder hat eine falsche Beschreibung!");
                            }
                        }
                        case "unload" -> {
                            try {
                                CCPlugin.getInstance().getServer().getPluginManager().disablePlugin(Objects.requireNonNull(CCPlugin.getInstance().getServer().getPluginManager().getPlugin(args[1])));

                                sender.sendMessage(CCPlugin.getPrefix() + "Plugin " + args[1] + " entladen!");
                            }
                            catch (NullPointerException e) {
                                sender.sendMessage(CCPlugin.getPrefix() + "Das Plugin " + args[1] + " existiert nicht!");
                            }
                        }
                        case "reload" -> {
                            try {
                                CCPlugin.getInstance().getServer().getPluginManager().disablePlugin(Objects.requireNonNull(CCPlugin.getInstance().getServer().getPluginManager().getPlugin(args[1])));
                            }
                            catch (NullPointerException e) {
                                sender.sendMessage(CCPlugin.getPrefix() + "Das Plugin " + args[1] + " existiert nicht!");
                                return true;
                            }

                            File dir = new File("./plugins");
                            File jar = new File(dir, args[1] + ".jar");

                            try {
                                try {
                                    CCPlugin.getInstance().getServer().getPluginManager().loadPlugin(jar);
                                    CCPlugin.getInstance().getServer().getPluginManager().enablePlugin(Objects.requireNonNull(CCPlugin.getInstance().getServer().getPluginManager().getPlugin(args[1])));
                                }
                                catch (NullPointerException e) {
                                    sender.sendMessage(CCPlugin.getPrefix() + "Konnte Datei nicht finden: " + args[1] + ".jar");
                                    return true;
                                }
                            }
                            catch (InvalidPluginException | InvalidDescriptionException e) {
                                sender.sendMessage(CCPlugin.getPrefix() + "Das Plugin ist entweder kein Plugin, oder hat eine falsche Beschreibung!");
                                return true;
                            }

                            sender.sendMessage(CCPlugin.getPrefix() + "Plugin " + args[1] + " neu geladen!");
                        }
                        default -> sender.sendMessage(CCPlugin.getPrefix() + "/plugin <load|unload|reload> <Name>");
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(CCPlugin.getPrefix() + "/plugin <load|unload|reload> <Name>");
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], Arrays.asList(LOADER), completions);
            Collections.sort(completions);
        }
        return completions;
    }
}
