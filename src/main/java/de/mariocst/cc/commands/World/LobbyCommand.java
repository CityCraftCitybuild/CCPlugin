package de.mariocst.cc.commands.World;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.LobbyData;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LobbyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            CCPlugin.getInstance().log("Dieser Command kann leider nur InGame ausgeführt werden!");
            return true;
        }

        if (player.hasPermission("mario.lobby") || player.hasPermission("*") || player.isOp()) {
            LobbyData lobbyData = LobbyData.getLobbyData();

            if (CCPlugin.getInstance().getServer().getWorld(lobbyData.getWorldName()) != null) {
                player.teleport(new Location(
                        CCPlugin.getInstance().getServer().getWorld(LobbyData.getLobbyData().getWorldName()),
                        lobbyData.getX(),
                        lobbyData.getY(),
                        lobbyData.getZ(),
                        lobbyData.getYaw(),
                        lobbyData.getPitch()));
            }
            else {
                if (player.isOp()) player.sendMessage(CCPlugin.getPrefix() + "Es gibt die Welt mit dem Namen \"" + lobbyData.getWorldName() +"\" nicht...");
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return true;
    }
}
