package de.mariocst.cc.commands.Others;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.DiscordLink;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            CCPlugin.getInstance().log("Unser Discord: " + DiscordLink.getDiscordLink().getLink());
            return false;
        }

        Player player = (Player) sender;
        if(player.hasPermission("mario.discord") || player.hasPermission("*") || player.isOp()) {
            sender.sendMessage(CCPlugin.getPrefix() + "Unser Discord: §a" + DiscordLink.getDiscordLink().getLink());
        } else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }
}
