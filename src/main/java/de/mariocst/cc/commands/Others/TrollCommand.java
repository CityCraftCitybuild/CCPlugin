package de.mariocst.cc.commands.Others;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TrollCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        String usage = "/troll <explosion|inventory|thunderstruck>";

        if (!(sender instanceof Player player)) {
            try {
                if (args.length >= 2) {
                    Player t = CCPlugin.getInstance().getServer().getPlayer(args[1]);

                    try {
                        if (t != null) {
                            switch (args[0].toLowerCase()) {
                                case "explosion" -> {
                                    if (args.length == 3) {
                                        float multiplier = Float.parseFloat(args[2]);
                                        float value = multiplier * 4.0f;

                                        if (multiplier > 45.0f) {
                                            sender.sendMessage(CCPlugin.getPrefix() + "Bitte gib eine kleinere Zahl ein!");
                                        }
                                        else if (multiplier <= 0.0f) {
                                            sender.sendMessage(CCPlugin.getPrefix() + "Bitte gib eine größere Zahl ein!");
                                        }
                                        else {
                                            t.getWorld().createExplosion(t.getLocation(), value);
                                            sender.sendMessage(CCPlugin.getPrefix() + "BOOM!");
                                        }
                                    }
                                    else {
                                        t.getWorld().createExplosion(t.getLocation(), 4.0f);
                                    }
                                }
                                case "inventory", "inv" -> {
                                    if (CCPlugin.getInstance().invTroll.contains(t)) {
                                        CCPlugin.getInstance().invTroll.remove(t);

                                        sender.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + t.getName() + " darf nun wieder sein Inventar benutzen!");

                                        for (int i = 0; i <= 35; i++) {
                                            t.getInventory().remove(Material.BARRIER);
                                        }
                                    }
                                    else {
                                        CCPlugin.getInstance().invTroll.add(t);

                                        sender.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + t.getName() + " darf nun nicht mehr sein Inventar benutzen!");

                                        for (int i = 0; i <= 35; i++) {
                                            if (t.getInventory().getItem(i) == null) {
                                                t.getInventory().setItem(i, new ItemStack(Material.BARRIER, 1));
                                            }
                                        }
                                    }
                                }
                                case "thunderstruck", "ts", "struck" -> {
                                    t.getWorld().strikeLightning(t.getLocation());

                                    sender.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + t.getName() + " hat einen Schlag!");
                                }
                                default -> sender.sendMessage(CCPlugin.getPrefix() + "/troll <explosion|inventory|thunderstruck> <Spieler>");
                            }
                        }
                        else {
                            sender.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                        }
                    }
                    catch (NullPointerException e) {
                        sender.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                    }
                }
                else {
                    sender.sendMessage(CCPlugin.getPrefix() + usage);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(CCPlugin.getPrefix() + usage);
            }
            return false;
        }

        if (player.hasPermission("mario.troll") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length >= 2) {
                    Player t = CCPlugin.getInstance().getServer().getPlayer(args[1]);

                    try {
                        if (t != null) {
                            switch (args[0].toLowerCase()) {
                                case "explosion" -> {
                                    if (args.length == 3) {
                                        float multiplier = Float.parseFloat(args[2]);
                                        float value = multiplier * 4.0f;

                                        if (multiplier > 45.0f) {
                                            player.sendMessage(CCPlugin.getPrefix() + "Bitte gib eine kleinere Zahl ein!");
                                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                                        }
                                        else if (multiplier <= 0.0f) {
                                            player.sendMessage(CCPlugin.getPrefix() + "Bitte gib eine größere Zahl ein!");
                                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                                        }
                                        else {
                                            t.getWorld().createExplosion(t.getLocation(), value);
                                            player.sendMessage(CCPlugin.getPrefix() + "BOOM!");
                                        }
                                    }
                                    else {
                                        t.getWorld().createExplosion(t.getLocation(), 4.0f);
                                    }
                                }
                                case "inventory", "inv" -> {
                                    if (CCPlugin.getInstance().invTroll.contains(t)) {
                                        CCPlugin.getInstance().invTroll.remove(t);

                                        player.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + t.getName() + " darf nun wieder sein Inventar benutzen!");
                                    }
                                    else {
                                        CCPlugin.getInstance().invTroll.add(t);

                                        player.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + t.getName() + " darf nun nicht mehr sein Inventar benutzen!");
                                    }
                                }
                                case "thunderstruck", "ts", "struck" -> {
                                    t.getWorld().strikeLightning(t.getLocation());

                                    player.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + t.getName() + " hat einen Schlag!");
                                }
                                default -> {
                                    player.sendMessage(CCPlugin.getPrefix() + "/troll <explosion|inventory|thunderstruck> <Spieler>");
                                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                                }
                            }
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
        return false;
    }

    private final String[] TROLLS = { "explosion", "inventory", "inv", "thunderstruck", "ts", "struck" };

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], Arrays.asList(TROLLS), completions);
            Collections.sort(completions);
        }
        else if (args.length == 2) {
            final List<String> names = new ArrayList<>();

            for (Player player : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                names.add(player.getName());
            }

            StringUtil.copyPartialMatches(args[1], names, completions);
            Collections.sort(completions);
        }
        return completions;
    }
}
