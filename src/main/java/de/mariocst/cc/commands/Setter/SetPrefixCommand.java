package de.mariocst.cc.commands.Setter;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.Prefix;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetPrefixCommand implements CommandExecutor {
    private String prefix;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String msg = "";

        if(!(sender instanceof Player)) {
            if (args.length >= 1) {
                for(int i = 0; i < args.length; i++) {
                    msg = msg + args[i] + " ";
                }
                prefix = msg;

                CCPlugin.getInstance().log("Der Prefix ist nun: " + prefix);
                CCPlugin.setPrefix(prefix.replaceAll("&", "§"));
                Prefix.getPrefixClass().setPrefix(prefix.replaceAll("&", "§"));
                CCPlugin.getInstance().saveConfigs();
            } else {
                CCPlugin.getInstance().log("§cUsage: §e/setprefix <Prefix>");
            }
            return false;
        }

        Player player = (Player) sender;
        if(player.hasPermission("mario.prefix") || player.hasPermission("*") || player.isOp()) {
            if (args.length >= 1) {
                for(int i = 0; i < args.length; i++) {
                    msg = msg + args[i] + " ";
                }
                prefix = msg;

                sender.sendMessage(CCPlugin.getPrefix() + "Der Prefix ist nun: " + prefix);
                CCPlugin.setPrefix(prefix.replaceAll("&", "§"));
                Prefix.getPrefixClass().setPrefix(prefix.replaceAll("&", "§"));
                CCPlugin.getInstance().saveConfigs();
            } else {
                sender.sendMessage("§cUsage: §e/setprefix <Prefix>");
            }
        } else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }
}
