package de.mariocst.cc.commands.Setter;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.DiscordLink;
import de.mariocst.cc.config.configdata.WebLink;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetLinkCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        StringBuilder msg = new StringBuilder();

        if (!(sender instanceof Player player)) {
            if (args.length >= 2) {
                switch (args[0].toLowerCase()) {
                    case "discord" -> {
                        for (int i = 1; i < args.length; i++) {
                            msg.append(args[i]).append(" ");
                        }

                        DiscordLink.getDiscordLink().setLink(msg.toString());
                        sender.sendMessage(CCPlugin.getPrefix() + "Der Discord Link ist nun: §a" + msg);
                        CCPlugin.getInstance().saveConfigs();
                    }
                    case "web" -> {
                        for (int i = 1; i < args.length; i++) {
                            msg.append(args[i]).append(" ");
                        }

                        WebLink.getWebLink().setLink(msg.toString());
                        sender.sendMessage(CCPlugin.getPrefix() + "Der Web Link ist nun: §a" + msg);
                        CCPlugin.getInstance().saveConfigs();
                    }
                    default -> sender.sendMessage("§cUsage: §e/setlink <discord|web> <Link>");
                }
            }
            else {
                CCPlugin.getInstance().log("§cUsage: §e/setlink <discord|web> <Link>");
            }
            return false;
        }

        if (player.hasPermission("mario.setlink") || player.hasPermission("*") || player.isOp()) {
            if (args.length >= 2) {
                switch (args[0].toLowerCase()) {
                    case "discord" -> {
                        for (int i = 1; i < args.length; i++) {
                            msg.append(args[i]).append(" ");
                        }

                        DiscordLink.getDiscordLink().setLink(msg.toString());
                        sender.sendMessage(CCPlugin.getPrefix() + "Der Discord Link ist nun: §a" + msg);
                        CCPlugin.getInstance().saveConfigs();
                    }
                    case "web" -> {
                        for (int i = 1; i < args.length; i++) {
                            msg.append(args[i]).append(" ");
                        }

                        WebLink.getWebLink().setLink(msg.toString());
                        sender.sendMessage(CCPlugin.getPrefix() + "Der Web Link ist nun: §a" + msg);
                        CCPlugin.getInstance().saveConfigs();
                    }
                    default -> sender.sendMessage("§cUsage: §e/setlink <discord|web> <Link>");
                }
            }
            else {
                sender.sendMessage("§cUsage: §e/setlink <discord|web> <Link>");
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }
}
