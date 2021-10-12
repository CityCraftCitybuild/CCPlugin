package de.mariocst.cc.commands.Player;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.FFAData;
import de.mariocst.cc.config.configdata.FlyWorlds;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        final String usage = "/fly [Spieler]";

        if (!(sender instanceof Player player)) {
            try {
                if (args.length == 1) {
                    Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                    try {
                        if (t != null) {
                            if (!(t.getAllowFlight())) {
                                t.setAllowFlight(true);
                                t.setFlying(true);
                                t.sendMessage(CCPlugin.getPrefix() + "§aDu fliegst nun.");
                                sender.sendMessage(CCPlugin.getPrefix() + "§aDer Spieler " + t.getName() + " fliegt nun.");
                            }
                            else {
                                t.setAllowFlight(false);
                                t.setFlying(false);
                                t.sendMessage(CCPlugin.getPrefix() + "§4Du fliegst nun nicht mehr.");
                                sender.sendMessage(CCPlugin.getPrefix() + "§4Der Spieler " + t.getName() + " fliegt nun nicht mehr.");
                            }
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
                else {
                    sender.sendMessage(CCPlugin.getPrefix() + usage);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(CCPlugin.getPrefix() + usage);
            }
            return false;
        }

        if (player.hasPermission("mario.fly") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length == 0) {
                    if (!FlyWorlds.getFlyWorlds().getWorlds().isEmpty()) {
                        if (player.hasPermission("mario.fly.bypass") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                            if (!(player.getAllowFlight())) {
                                player.setAllowFlight(true);
                                player.setFlying(true);
                                player.sendMessage(CCPlugin.getPrefix() + "§aDu fliegst nun.");
                            }
                            else {
                                player.setAllowFlight(false);
                                player.setFlying(false);
                                player.sendMessage(CCPlugin.getPrefix() + "§4Du fliegst nun nicht mehr.");
                            }
                            return false;
                        }

                        if (FlyWorlds.getFlyWorlds().getWorlds().contains(player.getWorld().getName()) && !player.getWorld().getName().equals("ffa")) {
                            if (!(player.getAllowFlight())) {
                                player.setAllowFlight(true);
                                player.setFlying(true);
                                player.sendMessage(CCPlugin.getPrefix() + "§aDu fliegst nun.");
                            }
                            else {
                                player.setAllowFlight(false);
                                player.setFlying(false);
                                player.sendMessage(CCPlugin.getPrefix() + "§4Du fliegst nun nicht mehr.");
                            }
                        }
                        else {
                            player.sendMessage(CCPlugin.getPrefix() + "Du bist nicht berechtigt, in dieser Welt zu fliegen!");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                        }
                    }
                    else {
                        if ((player.getWorld().getName().equalsIgnoreCase(FFAData.getFFAData().getWorldName()) || player.getWorld().getName().equals("ffa"))
                                && !player.hasPermission("mario.fly.bypass")
                                && !player.hasPermission("mario.*")
                                && !player.hasPermission("*")
                                && !player.isOp()) {
                            player.sendMessage(CCPlugin.getPrefix() + "Du darfst in FFA nicht fliegen!");
                            return true;
                        }

                        if (!(player.getAllowFlight())) {
                            player.setAllowFlight(true);
                            player.setFlying(true);
                            player.sendMessage(CCPlugin.getPrefix() + "§aDu fliegst nun.");
                        }
                        else {
                            player.setAllowFlight(false);
                            player.setFlying(false);
                            player.sendMessage(CCPlugin.getPrefix() + "§4Du fliegst nun nicht mehr.");
                        }
                    }
                }
                else if (args.length == 1) {
                    if (!player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) {
                        player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                        return false;
                    }

                    Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                    try {
                        if (t != null) {
                            if (!(t.getAllowFlight())) {
                                t.setAllowFlight(true);
                                t.setFlying(true);
                                t.sendMessage(CCPlugin.getPrefix() + "§aDu fliegst nun.");
                                player.sendMessage(CCPlugin.getPrefix() + "§aDer Spieler " + t.getName() + " fliegt nun.");
                            }
                            else {
                                t.setAllowFlight(false);
                                t.setFlying(false);
                                t.sendMessage(CCPlugin.getPrefix() + "§4Du fliegst nun nicht mehr.");
                                player.sendMessage(CCPlugin.getPrefix() + "§4Der Spieler " + t.getName() + " fliegt nun nicht mehr.");
                            }
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
            sender.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }
}
