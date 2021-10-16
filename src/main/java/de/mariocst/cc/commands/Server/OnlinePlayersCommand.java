package de.mariocst.cc.commands.Server;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import org.jetbrains.annotations.NotNull;

public class OnlinePlayersCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            if (CCPlugin.getInstance().getServer().getOnlinePlayers().isEmpty()) {
                sender.sendMessage(CCPlugin.getPrefix() + "Niemand ist auf dem Server!");
            }
            else {
                sender.sendMessage(CCPlugin.getPrefix() + "Online:");

                for (Player online : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                    if (FloodgateApi.getInstance().isFloodgatePlayer(online.getUniqueId())) {
                        FloodgatePlayer fPlayer = FloodgateApi.getInstance().getPlayer(online.getUniqueId());

                        sender.sendMessage(CCPlugin.getPrefix() + online.getName() + " (Bedrock: " + fPlayer.getDeviceOs().toString() + ")");
                    }
                    else {
                        sender.sendMessage(CCPlugin.getPrefix() + online.getName() + " (Java)");
                    }
                }
            }
            return true;
        }

        if (player.hasPermission("mario.onlineplayers") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            int i = 0;

            for (Player online : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                if (online != player) i++;
            }

            if (i == 0) {
                player.sendMessage(CCPlugin.getPrefix() + "Niemand, außer du, ist auf dem Server!");
                return true;
            }

            player.sendMessage(CCPlugin.getPrefix() + "Online:");

            for (Player online : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                if (FloodgateApi.getInstance().isFloodgatePlayer(online.getUniqueId())) {
                    FloodgatePlayer fPlayer = FloodgateApi.getInstance().getPlayer(online.getUniqueId());

                    player.sendMessage(CCPlugin.getPrefix() + online.getName() + " (Bedrock: " + fPlayer.getDeviceOs().toString() + ")");
                }
                else {
                    player.sendMessage(CCPlugin.getPrefix() + online.getName() + " (Java)");
                }
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }
}
