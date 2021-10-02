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

            for (int i = 0; i <= 35; i++) {
                player.getInventory().clear(i);

                if (inventoryData.getInventory().getItem(i) != null) {
                    player.getInventory().setItem(i, inventoryData.getInventory().getItem(i));

                    inventoryData.getInventory().clear(i);
                }
            }

            player.getInventory().setHelmet(new ItemStack(Material.AIR, 0));
            player.getInventory().setChestplate(new ItemStack(Material.AIR, 0));
            player.getInventory().setLeggings(new ItemStack(Material.AIR, 0));
            player.getInventory().setBoots(new ItemStack(Material.AIR, 0));

            ItemStack helmet = new ItemStack(Material.IRON_HELMET, 1);
            ItemMeta helmetMeta = helmet.getItemMeta();

            helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
            helmetMeta.setUnbreakable(true);

            helmet.setItemMeta(helmetMeta);

            ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemMeta chestplateMeta = chestplate.getItemMeta();

            chestplateMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
            chestplateMeta.setUnbreakable(true);

            chestplate.setItemMeta(chestplateMeta);

            ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemMeta leggingsMeta = leggings.getItemMeta();

            leggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
            leggingsMeta.setUnbreakable(true);

            leggings.setItemMeta(leggingsMeta);

            ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);
            ItemMeta bootsMeta = boots.getItemMeta();

            bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
            bootsMeta.setUnbreakable(true);

            boots.setItemMeta(bootsMeta);

            if (inventoryData.getInventory().getItem(36) == helmet) {
                inventoryData.getInventory().clear(36);
            }

            if (inventoryData.getInventory().getItem(37) == chestplate) {
                inventoryData.getInventory().clear(37);
            }

            if (inventoryData.getInventory().getItem(38) == leggings) {
                inventoryData.getInventory().clear(38);
            }

            if (inventoryData.getInventory().getItem(39) == boots) {
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
