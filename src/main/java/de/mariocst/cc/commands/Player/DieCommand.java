package de.mariocst.cc.commands.Player;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DieCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            CCPlugin.getInstance().log("Du bist gestorben. Warte... Du bist eine Konsole!");
            return true;
        }

        Player player = (Player) sender;

        if(player.hasPermission("mario.die") || player.hasPermission("*") || player.isOp()) {
            player.setHealth(0d);
            player.sendMessage(CCPlugin.getPrefix() + "Du bist gestorben.");
        } else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return true;
    }
}
