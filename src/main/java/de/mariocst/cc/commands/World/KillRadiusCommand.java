package de.mariocst.cc.commands.World;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;

public class KillRadiusCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            CCPlugin.getInstance().log("Dieser Command kann leider nur InGame ausgeführt werden!");
            return true;
        }

        if (player.hasPermission("mario.killradius") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length == 1) {
                    try {
                        int radius = Integer.parseInt(args[0]);

                        for (Entity entity : player.getWorld().getNearbyEntities(new BoundingBox(
                                player.getLocation().getX() - radius,
                                player.getLocation().getY() - radius,
                                player.getLocation().getZ() - radius,
                                player.getLocation().getX() + radius,
                                player.getLocation().getY() + radius,
                                player.getLocation().getZ() + radius
                        ))) {
                            if (!(entity instanceof Player)) entity.remove();
                        }
                    }
                    catch (NumberFormatException e) {
                        e.printStackTrace();
                        player.sendMessage(CCPlugin.getPrefix() + "Bitte gib eine ganze Zahl ein!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                    }
                }
                else {
                    player.sendMessage(CCPlugin.getPrefix() + "/kr <Radius>");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(CCPlugin.getPrefix() + "/kr <Radius>");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return true;
    }
}
