package de.mariocst.cc.commands.Others;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ECCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            CCPlugin.getInstance().log("Dieser Command kann nur InGame ausgeführt werden!");
            return false;
        }

        Player player = (Player) sender;
        if(player.hasPermission("mario.ec") || player.hasPermission("*") || player.isOp()) {
            player.openInventory(player.getEnderChest());
        } else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }
}
