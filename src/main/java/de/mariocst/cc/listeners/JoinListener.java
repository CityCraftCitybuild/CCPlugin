package de.mariocst.cc.listeners;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.FFAData;
import de.mariocst.cc.config.configdata.InventoryData;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.joinMessage(Component.text(""));
        CCPlugin.getInstance().getLogger().warning(player.getName() + " joined the game!");

        for (Player player1 : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
            player1.sendMessage(CCPlugin.getPrefix() + player.getName() + " ist dem Server beigetreten!");
        }

        if (player.getWorld().getName().equalsIgnoreCase(FFAData.getFFAData().getWorldName())) {
            if(player.getAllowFlight() && player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR) {
                player.setAllowFlight(false);
                player.setFlying(false);
                player.sendMessage(CCPlugin.getPrefix() + "§4Du fliegst nun nicht mehr.");
            }

            FFAData ffaData = FFAData.getFFAData();

            player.teleport(new Location(
                    CCPlugin.getInstance().getServer().getWorld(ffaData.getWorldName()),
                    ffaData.getX(),
                    ffaData.getY(),
                    ffaData.getZ(),
                    ffaData.getYaw(),
                    ffaData.getPitch()));

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
        }
    }
}
