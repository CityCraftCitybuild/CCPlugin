package de.mariocst.cc.commands.World;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.EndData;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EndCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            CCPlugin.getInstance().log("Dieser Command kann leider nur InGame ausgeführt werden!");
            return true;
        }

        if (player.hasPermission("mario.end") || player.hasPermission("*") || player.isOp()) {
            EndData endData = EndData.getEndData();

            if (CCPlugin.getInstance().getServer().getWorld(endData.getWorldName()) != null) {
                player.teleport(new Location(
                        CCPlugin.getInstance().getServer().getWorld(endData.getWorldName()),
                        endData.getX(),
                        endData.getY(),
                        endData.getZ(),
                        endData.getYaw(),
                        endData.getPitch()));
            }
            else {
                if (player.isOp()) player.sendMessage(CCPlugin.getPrefix() + "Es gibt aus irgendeinem Grund die Welt mit dem Namen \"" + endData.getWorldName() + "\" nicht...");
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return true;
    }
}
