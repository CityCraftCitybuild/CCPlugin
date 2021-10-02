package de.mariocst.cc.listeners;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.FFAData;
import de.mariocst.cc.config.configdata.InventoryData;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WorldChangeListener implements Listener {
    @EventHandler
    public void onChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        assert CCPlugin.getInstance().getServer().getWorld(FFAData.getFFAData().getWorldName()) != null;

        if (player.getWorld().getName().equalsIgnoreCase(FFAData.getFFAData().getWorldName())) {
            if(player.getAllowFlight() && player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR) {
                player.setAllowFlight(false);
                player.setFlying(false);
                player.sendMessage(CCPlugin.getPrefix() + "§4Du fliegst nun nicht mehr.");
            }

            InventoryData inventoryData = CCPlugin.getInstance().getInventoryDataManager().getInventory(player.getUniqueId());

            for (int i = 0; i <= 35; i++) {
                if (player.getInventory().getItem(i) != null) {
                    inventoryData.getInventory().setItem(i, player.getInventory().getItem(i));

                    player.getInventory().clear(i);
                }
                else {
                    inventoryData.getInventory().setItem(i, new ItemStack(Material.AIR, 0));
                }

                switch (i) {
                    case 0 -> {
                        ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
                        ItemMeta swordMeta = sword.getItemMeta();

                        swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, false);
                        swordMeta.setUnbreakable(true);

                        sword.setItemMeta(swordMeta);

                        player.getInventory().setItem(i, sword);
                    }
                    case 1 -> {
                        ItemStack bow = new ItemStack(Material.BOW, 1);
                        ItemMeta bowMeta = bow.getItemMeta();

                        bowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
                        bowMeta.setUnbreakable(true);

                        bow.setItemMeta(bowMeta);

                        player.getInventory().setItem(i, bow);
                    }
                    case 2 -> player.getInventory().setItem(i, new ItemStack(Material.ARROW, 16));
                }
            }

            for (int i = 36; i <= 39; i++) {
                switch (i) {
                    case 36 -> {
                        if (player.getInventory().getHelmet() != null) {
                            inventoryData.getInventory().setItem(36, player.getInventory().getHelmet());

                            player.getInventory().setHelmet(new ItemStack(Material.AIR, 0));
                        }
                        else {
                            inventoryData.getInventory().setItem(36, new ItemStack(Material.AIR, 0));
                        }

                        ItemStack helmet = new ItemStack(Material.IRON_HELMET, 1);
                        ItemMeta helmetMeta = helmet.getItemMeta();

                        helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
                        helmetMeta.setUnbreakable(true);

                        helmet.setItemMeta(helmetMeta);

                        player.getInventory().setHelmet(helmet);
                    }
                    case 37 -> {
                        if (player.getInventory().getChestplate() != null) {
                            inventoryData.getInventory().setItem(37, player.getInventory().getChestplate());

                            player.getInventory().setChestplate(new ItemStack(Material.AIR, 0));
                        }
                        else {
                            inventoryData.getInventory().setItem(37, new ItemStack(Material.AIR, 0));
                        }

                        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
                        ItemMeta chestplateMeta = chestplate.getItemMeta();

                        chestplateMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
                        chestplateMeta.setUnbreakable(true);

                        chestplate.setItemMeta(chestplateMeta);

                        player.getInventory().setChestplate(chestplate);
                    }
                    case 38 -> {
                        if (player.getInventory().getLeggings() != null) {
                            inventoryData.getInventory().setItem(38, player.getInventory().getLeggings());

                            player.getInventory().setLeggings(new ItemStack(Material.AIR, 0));
                        }
                        else {
                            inventoryData.getInventory().setItem(38, new ItemStack(Material.AIR, 0));
                        }

                        ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS, 1);
                        ItemMeta leggingsMeta = leggings.getItemMeta();

                        leggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
                        leggingsMeta.setUnbreakable(true);

                        leggings.setItemMeta(leggingsMeta);

                        player.getInventory().setLeggings(leggings);
                    }
                    case 39 -> {
                        if (player.getInventory().getBoots() != null) {
                            inventoryData.getInventory().setItem(39, player.getInventory().getBoots());

                            player.getInventory().setBoots(new ItemStack(Material.AIR, 0));
                        }
                        else {
                            inventoryData.getInventory().setItem(39, new ItemStack(Material.AIR, 0));
                        }

                        ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);
                        ItemMeta bootsMeta = boots.getItemMeta();

                        bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
                        bootsMeta.setUnbreakable(true);

                        boots.setItemMeta(bootsMeta);

                        player.getInventory().setBoots(boots);
                    }
                }
            }

            inventoryData.getInventory().setItem(40, player.getInventory().getItemInOffHand());

            player.getInventory().setItemInOffHand(new ItemStack(Material.AIR, 0));

            CCPlugin.getInstance().getInventoryDataManager().save();
        } else if (event.getFrom().getName().equalsIgnoreCase(FFAData.getFFAData().getWorldName())) {
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

            int armor = 0;

            if (inventoryData.getInventory().getItem(36) == helmet) {
                inventoryData.getInventory().clear(36);

                armor++;
            }

            if (inventoryData.getInventory().getItem(37) == chestplate) {
                inventoryData.getInventory().clear(37);

                armor++;
            }

            if (inventoryData.getInventory().getItem(38) == leggings) {
                inventoryData.getInventory().clear(38);

                armor++;
            }

            if (inventoryData.getInventory().getItem(39) == boots) {
                inventoryData.getInventory().clear(39);

                armor++;
            }

            if (armor != 0) {
                player.sendMessage(CCPlugin.getPrefix() + "Mit /ffaarmor bekommst du deine Rüstung wieder!");
            }

            if (inventoryData.getInventory().getItem(40) != null) {
                player.getInventory().setItemInOffHand(inventoryData.getInventory().getItem(40));

                inventoryData.getInventory().clear(40);
            }

            CCPlugin.getInstance().getInventoryDataManager().save();
        }
    }
}
