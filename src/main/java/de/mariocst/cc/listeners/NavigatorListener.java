package de.mariocst.cc.listeners;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.Objects;

public class NavigatorListener implements Listener {
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if (player.getOpenInventory().title().equals(Component.text("§cNavigator"))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (player.getOpenInventory().title().equals(Component.text("§cNavigator"))) {
            if (event.getCurrentItem() == null || event.getClickedInventory() == player.getInventory()) return;

            event.setCancelled(true);

            if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§2Farmwelt"))) {
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
                    if (player.isOp()) {
                        player.sendMessage(CCPlugin.getPrefix() + "Es gibt aus irgendeinem Grund die Welt mit dem Namen \"" + farmweltData.getWorldName() + "\" nicht...");
                    }
                }

                player.closeInventory();
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§cNether"))) {
                NetherData netherData = NetherData.getNetherData();

                if (CCPlugin.getInstance().getServer().getWorld(netherData.getWorldName()) != null) {
                    player.teleport(new Location(
                            CCPlugin.getInstance().getServer().getWorld(netherData.getWorldName()),
                            netherData.getX(),
                            netherData.getY(),
                            netherData.getZ(),
                            netherData.getYaw(),
                            netherData.getPitch()));
                }
                else {
                    if (player.isOp()) {
                        player.sendMessage(CCPlugin.getPrefix() + "Es gibt aus irgendeinem Grund die Welt mit dem Namen \"" + netherData.getWorldName() + "\" nicht...");
                    }
                }

                player.closeInventory();
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§eEnd"))) {
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
                    if (player.isOp()) {
                        player.sendMessage(CCPlugin.getPrefix() + "Es gibt aus irgendeinem Grund die Welt mit dem Namen \"" + endData.getWorldName() + "\" nicht...");
                    }
                }

                player.closeInventory();
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§3CityBuild"))) {
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
                    if (player.isOp()) {
                        player.sendMessage(CCPlugin.getPrefix() + "Es gibt die Welt mit dem Namen \"" + cb01Data.getWorldName() + "\" nicht...");
                    }
                }

                player.closeInventory();
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§bLobby"))) {
                LobbyData lobbyData = LobbyData.getLobbyData();

                if (CCPlugin.getInstance().getServer().getWorld(lobbyData.getWorldName()) != null) {
                    player.teleport(new Location(
                            CCPlugin.getInstance().getServer().getWorld(lobbyData.getWorldName()),
                            lobbyData.getX(),
                            lobbyData.getY(),
                            lobbyData.getZ(),
                            lobbyData.getYaw(),
                            lobbyData.getPitch()));
                }
                else {
                    if (player.isOp()) {
                        player.sendMessage(CCPlugin.getPrefix() + "Es gibt aus irgendeinem Grund die Welt mit dem Namen \"" + lobbyData.getWorldName() + "\" nicht...");
                    }
                }

                player.closeInventory();
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§4FFA"))) {
                FFAData ffaData = FFAData.getFFAData();

                if (CCPlugin.getInstance().getServer().getWorld(ffaData.getWorldName()) != null) {
                    player.teleport(new Location(
                            CCPlugin.getInstance().getServer().getWorld(ffaData.getWorldName()),
                            ffaData.getX(),
                            ffaData.getY(),
                            ffaData.getZ(),
                            ffaData.getYaw(),
                            ffaData.getPitch()));
                }
                else {
                    if (player.isOp()) {
                        player.sendMessage(CCPlugin.getPrefix() + "Es gibt aus irgendeinem Grund die Welt mit dem Namen \"" + ffaData.getWorldName() + "\" nicht...");
                    }
                }

                player.closeInventory();
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§cSchließen"))) {
                player.closeInventory();
            }
        }
    }
}
