package de.mariocst.cc.commands.Setter;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.Prefix;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetPrefixCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        StringBuilder msg = new StringBuilder();
        String prefix;

        if (!(sender instanceof Player player)) {
            if (args.length >= 1) {
                for (String arg : args) {
                    msg.append(arg).append(" ");
                }

                prefix = msg.toString();

                CCPlugin.getInstance().log("Der Prefix ist nun: " + prefix);
                CCPlugin.setPrefix(prefix.replaceAll("&", "§"));
                Prefix.getPrefixClass().setPrefix(prefix.replaceAll("&", "§"));
                CCPlugin.getInstance().saveConfigs();
            }
            else {
                CCPlugin.getInstance().log("§cUsage: §e/setprefix <Prefix>");
            }
            return false;
        }

        if (player.hasPermission("mario.prefix") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            if (args.length >= 1) {
                for (String arg : args) {
                    msg.append(arg).append(" ");
                }

                prefix = msg.toString();

                player.sendMessage(CCPlugin.getPrefix() + "Der Prefix ist nun: " + prefix);
                CCPlugin.setPrefix(prefix.replaceAll("&", "§"));
                Prefix.getPrefixClass().setPrefix(prefix.replaceAll("&", "§"));
                CCPlugin.getInstance().saveConfigs();
            }
            else {
                player.sendMessage("§cUsage: §e/setprefix <Prefix>");
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }
}
