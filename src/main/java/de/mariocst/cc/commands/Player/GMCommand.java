package de.mariocst.cc.commands.Player;

import de.mariocst.cc.CCPlugin;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            String msg = String.join(" ", args);

            try {
                if (args.length == 2) {
                    Player t = CCPlugin.getInstance().getServer().getPlayer(args[1]);

                    try {
                        if (t != null) {
                            if(msg.equals("0") || msg.equalsIgnoreCase("survival") || msg.equalsIgnoreCase("su")) {
                                t.setGameMode(GameMode.SURVIVAL);
                                t.sendMessage(CCPlugin.getPrefix() + "Dein Gamemode wurde auf Survival gestellt!");
                                sender.sendMessage(CCPlugin.getPrefix() + "Der Gamemode von " + t.getName() + " ist auf Survival gesetzt worden!");
                            } else if (msg.equals("1") || msg.equalsIgnoreCase("creative") || msg.equalsIgnoreCase("c")) {
                                t.setGameMode(GameMode.CREATIVE);
                                t.sendMessage(CCPlugin.getPrefix() + "Dein Gamemode wurde auf Creative gestellt!");
                                sender.sendMessage(CCPlugin.getPrefix() + "Der Gamemode von " + t.getName() + " ist auf Creative gesetzt worden!");
                            } else if (msg.equals("2") || msg.equalsIgnoreCase("adventure") || msg.equalsIgnoreCase("a")) {
                                t.setGameMode(GameMode.ADVENTURE);
                                t.sendMessage(CCPlugin.getPrefix() + "Dein Gamemode wurde auf Adventure gestellt!");
                                sender.sendMessage(CCPlugin.getPrefix() + "Der Gamemode von " + t.getName() + " ist auf Adventure gesetzt worden!");
                            } else if (msg.equals("3") || msg.equalsIgnoreCase("spectator") || msg.equalsIgnoreCase("sp")) {
                                t.setGameMode(GameMode.SPECTATOR);
                                t.sendMessage(CCPlugin.getPrefix() + "Dein Gamemode wurde auf Spectator gestellt!");
                                sender.sendMessage(CCPlugin.getPrefix() + "Der Gamemode von " + t.getName() + " ist auf Spectator gesetzt worden!");
                            } else {
                                sender.sendMessage(CCPlugin.getPrefix() + "Bitte gib einen gültigen Gamemode ein!");
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
                    sender.sendMessage(CCPlugin.getPrefix() + "Usage: /gm 0 oder 1 oder 2 oder 3 oder survival oder creative oder adventure oder spectator oder su oder c oder a oder sp");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(CCPlugin.getPrefix() + "Usage: /gm 0 oder 1 oder 2 oder 3 oder survival oder creative oder adventure oder spectator oder su oder c oder a oder sp");
            }
            return false;
        }

        Player player = (Player) sender;
        if(player.hasPermission("mario.gm") || player.hasPermission("*") || player.isOp()) {
            String msg = String.join(" ", args);

            boolean b = msg.equals("0") || msg.equalsIgnoreCase("survival") || msg.equalsIgnoreCase("su");
            boolean b1 = msg.equals("1") || msg.equalsIgnoreCase("creative") || msg.equalsIgnoreCase("c");
            boolean b2 = msg.equals("2") || msg.equalsIgnoreCase("adventure") || msg.equalsIgnoreCase("a");
            boolean b3 = msg.equals("3") || msg.equalsIgnoreCase("spectator") || msg.equalsIgnoreCase("sp");

            try {
                if (args.length == 1) {
                    if(b) {
                        player.setGameMode(GameMode.SURVIVAL);
                        sender.sendMessage(CCPlugin.getPrefix() + "Dein Gamemode wurde auf Survival gestellt!");
                    } else if (b1) {
                        player.setGameMode(GameMode.CREATIVE);
                        sender.sendMessage(CCPlugin.getPrefix() + "Dein Gamemode wurde auf Creative gestellt!");
                    } else if (b2) {
                        player.setGameMode(GameMode.ADVENTURE);
                        sender.sendMessage(CCPlugin.getPrefix() + "Dein Gamemode wurde auf Adventure gestellt!");
                    } else if (b3) {
                        player.setGameMode(GameMode.SPECTATOR);
                        sender.sendMessage(CCPlugin.getPrefix() + "Dein Gamemode wurde auf Spectator gestellt!");
                    } else {
                        sender.sendMessage(CCPlugin.getPrefix() + "Bitte gib einen gültigen Gamemode ein!");
                    }
                }
                else if (args.length == 2) {
                    Player t = CCPlugin.getInstance().getServer().getPlayer(args[1]);

                    try {
                        if (t != null) {
                            if(b) {
                                t.setGameMode(GameMode.SURVIVAL);
                                t.sendMessage(CCPlugin.getPrefix() + "Dein Gamemode wurde auf Survival gestellt!");
                                sender.sendMessage(CCPlugin.getPrefix() + "Der Gamemode von " + t.getName() + " ist auf Survival gesetzt worden!");
                            } else if (b1) {
                                t.setGameMode(GameMode.CREATIVE);
                                t.sendMessage(CCPlugin.getPrefix() + "Dein Gamemode wurde auf Creative gestellt!");
                                sender.sendMessage(CCPlugin.getPrefix() + "Der Gamemode von " + t.getName() + " ist auf Creative gesetzt worden!");
                            } else if (b2) {
                                t.setGameMode(GameMode.ADVENTURE);
                                t.sendMessage(CCPlugin.getPrefix() + "Dein Gamemode wurde auf Adventure gestellt!");
                                sender.sendMessage(CCPlugin.getPrefix() + "Der Gamemode von " + t.getName() + " ist auf Adventure gesetzt worden!");
                            } else if (b3) {
                                t.setGameMode(GameMode.SPECTATOR);
                                t.sendMessage(CCPlugin.getPrefix() + "Dein Gamemode wurde auf Spectator gestellt!");
                                sender.sendMessage(CCPlugin.getPrefix() + "Der Gamemode von " + t.getName() + " ist auf Spectator gesetzt worden!");
                            } else {
                                sender.sendMessage(CCPlugin.getPrefix() + "Bitte gib einen gültigen Gamemode ein!");
                            }
                        }
                        else {
                            sender.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        sender.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                    }
                }
                else {
                    sender.sendMessage(CCPlugin.getPrefix() + "Usage: /gm 0 oder 1 oder 2 oder 3 oder survival oder creative oder adventure oder spectator oder su oder c oder a oder sp");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(CCPlugin.getPrefix() + "Usage: /gm 0 oder 1 oder 2 oder 3 oder survival oder creative oder adventure oder spectator oder su oder c oder a oder sp");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
            }
        } else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }
}
