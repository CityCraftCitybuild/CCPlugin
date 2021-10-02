package de.mariocst.cc.commands.World;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.CB01Data;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CBCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            CCPlugin.getInstance().log("Dieser Command kann leider nur InGame ausgeführt werden!");
            return true;
        }

        if (player.hasPermission("mario.cb") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            CB01Data cb01Data = CB01Data.getCB01Data();

            if (CCPlugin.getInstance().getServer().getWorld(cb01Data.getWorldName()) != null) {
                player.teleport(new Location(
                        CCPlugin.getInstance().getServer().getWorld(cb01Data.getWorldName()),
                        cb01Data.getX(),
                        cb01Data.getY(),
                        cb01Data.getZ(),
                        cb01Data.getYaw(),
                        cb01Data.getPitch()));
            }
            else {
                if (player.isOp()) player.sendMessage(CCPlugin.getPrefix() + "Es gibt die Welt mit dem Namen \"" + cb01Data.getWorldName() + "\" nicht...");
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return true;
    }
}
