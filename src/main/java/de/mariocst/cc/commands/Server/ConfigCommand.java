package de.mariocst.cc.commands.Server;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConfigCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            try {
                if (args.length == 1) {
                    switch (args[0].toLowerCase()) {
                        case "save" -> {
                            CCPlugin.getInstance().saveConfigs();
                            sender.sendMessage(CCPlugin.getPrefix() + "Configs gespeichert!");
                        }
                        case "reload" -> {
                            CCPlugin.getInstance().reloadConfigs();
                            sender.sendMessage(CCPlugin.getPrefix() + "Configs neu geladen!");
                        }
                        default -> {
                            sender.sendMessage(CCPlugin.getPrefix() + "/config <save|reload>");
                        }
                    }
                }
                else {
                    sender.sendMessage(CCPlugin.getPrefix() + "/config <save|reload>");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(CCPlugin.getPrefix() + "/config <save|reload>");
            }
            return true;
        }

        Player player = (Player) sender;

        if(player.hasPermission("mario.config") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length == 1) {
                    switch (args[0].toLowerCase()) {
                        case "save" -> {
                            CCPlugin.getInstance().saveConfigs();
                            player.sendMessage(CCPlugin.getPrefix() + "Configs gespeichert!");
                        }
                        case "reload" -> {
                            CCPlugin.getInstance().reloadConfigs();
                            player.sendMessage(CCPlugin.getPrefix() + "Configs neu geladen!");
                        }
                        default -> {
                            player.sendMessage(CCPlugin.getPrefix() + "/config <save|reload>");
                        }
                    }
                }
                else {
                    player.sendMessage(CCPlugin.getPrefix() + "/config <save|reload>");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(CCPlugin.getPrefix() + "/config <save|reload>");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
            }
        } else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return true;
    }
}
