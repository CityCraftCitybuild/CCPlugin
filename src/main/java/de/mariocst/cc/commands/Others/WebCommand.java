package de.mariocst.cc.commands.Others;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.WebLink;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WebCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            CCPlugin.getInstance().log("Unser Web: " + WebLink.getWebLink().getLink());
            return false;
        }

        Player player = (Player) sender;
        if(player.hasPermission("mario.web") || player.hasPermission("*") || player.isOp()) {
            sender.sendMessage(CCPlugin.getPrefix() + "Unser Web: §a" + WebLink.getWebLink().getLink());
        } else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }
}
