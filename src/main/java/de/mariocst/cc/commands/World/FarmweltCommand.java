package de.mariocst.cc.commands.World;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.FarmweltData;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FarmweltCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            CCPlugin.getInstance().log("Dieser Command kann leider nur InGame ausgeführt werden!");
            return true;
        }

        if (player.hasPermission("mario.farmwelt") || player.hasPermission("*") || player.isOp()) {
            FarmweltData farmweltData = FarmweltData.getFarmweltData();

            if (CCPlugin.getInstance().getServer().getWorld(farmweltData.getWorldName()) != null) {
                player.teleport(new Location(
                        CCPlugin.getInstance().getServer().getWorld(farmweltData.getWorldName()),
                        farmweltData.getX(),
                        farmweltData.getY(),
                        farmweltData.getZ(),
                        farmweltData.getYaw(),
                        farmweltData.getPitch()));
            }
            else {
                if (player.isOp()) player.sendMessage(CCPlugin.getPrefix() + "Es gibt aus irgendeinem Grund die Welt mit dem Namen \"" + farmweltData.getWorldName() + "\" nicht...");
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return true;
    }
}
