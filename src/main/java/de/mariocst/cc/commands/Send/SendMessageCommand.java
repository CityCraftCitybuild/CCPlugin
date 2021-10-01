package de.mariocst.cc.commands.Send;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendMessageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            try {
                if (args.length >= 2) {
                    Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                    try {
                        if (t != null) {
                            String msg = "";
                            for(int i = 1; i < args.length; i++) {
                                msg = msg + args[i] + " ";
                            }

                            t.sendMessage(CCPlugin.getPrefix() + msg.replaceAll("&", "§"));
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        sender.sendMessage("Dieser Spieler existiert nicht!");
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage("/sm <Spieler> <Nachricht>");
            }
            return true;
        }

        Player player = (Player) sender;

        if(player.hasPermission("mario.sendmessage") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length >= 2) {
                    Player t = player.getServer().getPlayer(args[0]);

                    try {
                        if (t != null) {
                            String msg = "";
                            for(int i = 1; i < args.length; i++) {
                                msg = msg + args[i] + " ";
                            }

                            t.sendMessage(CCPlugin.getPrefix() + msg.replaceAll("&", "§"));
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        player.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(CCPlugin.getPrefix() + "/sm <Spieler> <Nachricht>");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
            }
        } else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return true;
    }
}
