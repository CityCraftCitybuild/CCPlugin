package de.mariocst.cc.commands.Player;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodmodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            CCPlugin.getInstance().log("Bitte führe den Command InGame aus!");
            return true;
        }

        Player player = (Player) sender;

        if(player.hasPermission("mario.godmode") || player.hasPermission("*") || player.isOp()) {
            if (CCPlugin.getInstance().godmode.contains(player)) {
                CCPlugin.getInstance().godmode.remove(player);

                player.sendMessage(CCPlugin.getPrefix() + "Du bist nun nicht mehr unbesiegbar!");
            }
            else {
                CCPlugin.getInstance().godmode.add(player);

                player.sendMessage(CCPlugin.getPrefix() + "Du bist nun unbesiegbar!");
            }
        } else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return true;
    }
}
