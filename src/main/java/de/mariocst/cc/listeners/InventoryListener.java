package de.mariocst.cc.listeners;

import de.mariocst.cc.CCPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class InventoryListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        try {
            for (Player ignored : CCPlugin.getInstance().invTroll) {
                if (event.getClickedInventory() == player.getInventory()) {
                    event.setCancelled(true);
                }
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        for (Player ignored : CCPlugin.getInstance().invTroll) {
            event.setCancelled(true);
            event.getItemDrop().remove();
        }
    }

    @EventHandler
    public void onMove(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (player.getOpenInventory().title().equals(Component.text("Deine Rüstung"))) {
            try {
                if (event.getClickedInventory() != player.getInventory() && event.getCurrentItem() != null) {
                    if (event.getSlot() == 0) {
                        CCPlugin.getInstance().getInventoryDataManager().getInventory(player.getUniqueId()).getInventory().clear(36);
                    }
                    if (event.getSlot() == 1) {
                        CCPlugin.getInstance().getInventoryDataManager().getInventory(player.getUniqueId()).getInventory().clear(37);
                    }
                    if (event.getSlot() == 2) {
                        CCPlugin.getInstance().getInventoryDataManager().getInventory(player.getUniqueId()).getInventory().clear(38);
                    }
                    if (event.getSlot() == 3) {
                        CCPlugin.getInstance().getInventoryDataManager().getInventory(player.getUniqueId()).getInventory().clear(39);
                    }

                    CCPlugin.getInstance().getInventoryDataManager().save();
                }
            }
            catch (NullPointerException ignored) { }
        }
    }
}
