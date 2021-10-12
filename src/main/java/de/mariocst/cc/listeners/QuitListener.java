package de.mariocst.cc.listeners;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.FFAData;
import de.mariocst.cc.config.configdata.InventoryData;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class QuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.quitMessage(Component.text(""));
        CCPlugin.getInstance().getLogger().warning(player.getName() + " left the game!");

        for (Player player1 : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
            player1.sendMessage(CCPlugin.getPrefix() + player.getName() + " hat den Server verlassen!");
        }

        if (player.getWorld().getName().equalsIgnoreCase(FFAData.getFFAData().getWorldName())) {
            InventoryData inventoryData = CCPlugin.getInstance().getInventoryDataManager().getInventory(player.getUniqueId());

            player.getInventory().clear();

            for (int i = 0; i <= 35; i++) {
                if (inventoryData.getInventory().getItem(i) != null) {
                    player.getInventory().setItem(i, inventoryData.getInventory().getItem(i));

                    inventoryData.getInventory().clear(i);
                }
            }

            if (inventoryData.getInventory().getItem(36) != null) {
                player.getInventory().setHelmet(inventoryData.getInventory().getItem(36));
                inventoryData.getInventory().clear(36);
            }

            if (inventoryData.getInventory().getItem(37) != null) {
                player.getInventory().setChestplate(inventoryData.getInventory().getItem(37));
                inventoryData.getInventory().clear(37);
            }

            if (inventoryData.getInventory().getItem(38) != null) {
                player.getInventory().setLeggings(inventoryData.getInventory().getItem(38));
                inventoryData.getInventory().clear(38);
            }

            if (inventoryData.getInventory().getItem(39) != null) {
                player.getInventory().setBoots(inventoryData.getInventory().getItem(39));
                inventoryData.getInventory().clear(39);
            }

            if (inventoryData.getInventory().getItem(40) != null) {
                player.getInventory().setItemInOffHand(inventoryData.getInventory().getItem(40));

                inventoryData.getInventory().clear(40);
            }

            CCPlugin.getInstance().getInventoryDataManager().save();
        }
    }
}
