package de.mariocst.cc.commands.Storing;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.FFAData;
import de.mariocst.cc.config.configdata.InventoryData;
import de.mariocst.cc.config.configdata.InventoryDataManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FFAArmorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CCPlugin.getPrefix() + "Dieser Command geht nur InGame!");
            return true;
        } else {
            Player player = (Player) sender;

            if(player.hasPermission("mario.ffaarmor") || player.hasPermission("mario.*") || player.isOp()) {
                if (!player.getWorld().getName().equalsIgnoreCase(FFAData.getFFAData().getWorldName()) || player.isOp()) {
                    player.closeInventory();

                    Inventory inventory = CCPlugin.getInstance().getServer().createInventory(player, 9, Component.join(Component.text().content("Deine Rüstung")));

                    ItemStack empty = new ItemStack(Material.AIR, 0);
                    Inventory inventoryData = CCPlugin.getInstance().getInventoryDataManager().getInventory(player.getUniqueId()).getInventory();

                    ItemStack helmet = new ItemStack(Material.AIR, 0);
                    ItemStack chestplate = new ItemStack(Material.AIR, 0);
                    ItemStack leggings = new ItemStack(Material.AIR, 0);
                    ItemStack boots = new ItemStack(Material.AIR, 0);

                    if (inventoryData.getItem(36) != null) {
                        helmet = new ItemStack(Objects.requireNonNull(inventoryData.getItem(36)).getType(), 1);
                        ItemMeta helmetMeta = Objects.requireNonNull(inventoryData.getItem(36)).getItemMeta();

                        helmet.setItemMeta(helmetMeta);

                        if (!Objects.requireNonNull(inventoryData.getItem(36)).getEnchantments().isEmpty()) {
                            helmet.addEnchantments(Objects.requireNonNull(inventoryData.getItem(36)).getEnchantments());
                        }
                    }

                    if (inventoryData.getItem(37) != null) {
                        chestplate = new ItemStack(Objects.requireNonNull(inventoryData.getItem(37)).getType(), 1);
                        ItemMeta chestplateMeta = Objects.requireNonNull(inventoryData.getItem(37)).getItemMeta();

                        chestplate.setItemMeta(chestplateMeta);

                        if (!Objects.requireNonNull(inventoryData.getItem(37)).getEnchantments().isEmpty()) {
                            chestplate.addEnchantments(Objects.requireNonNull(inventoryData.getItem(37)).getEnchantments());
                        }
                    }

                    if (inventoryData.getItem(38) != null) {
                        leggings = new ItemStack(Objects.requireNonNull(inventoryData.getItem(38)).getType(), 1);
                        ItemMeta leggingsMeta = Objects.requireNonNull(inventoryData.getItem(38)).getItemMeta();

                        leggings.setItemMeta(leggingsMeta);

                        if (!Objects.requireNonNull(inventoryData.getItem(38)).getEnchantments().isEmpty()) {
                            leggings.addEnchantments(Objects.requireNonNull(inventoryData.getItem(38)).getEnchantments());
                        }
                    }

                    if (inventoryData.getItem(39) != null) {
                        boots = new ItemStack(Objects.requireNonNull(inventoryData.getItem(39)).getType(), 1);
                        ItemMeta bootsMeta = Objects.requireNonNull(inventoryData.getItem(39)).getItemMeta();

                        boots.setItemMeta(bootsMeta);

                        if (!Objects.requireNonNull(inventoryData.getItem(39)).getEnchantments().isEmpty()) {
                            boots.addEnchantments(Objects.requireNonNull(inventoryData.getItem(39)).getEnchantments());
                        }
                    }

                    inventory.setItem(0, helmet);
                    inventory.setItem(1, chestplate);
                    inventory.setItem(2, leggings);
                    inventory.setItem(3, boots);
                    inventory.setItem(4, empty);
                    inventory.setItem(5, empty);
                    inventory.setItem(6, empty);
                    inventory.setItem(7, empty);
                    inventory.setItem(8, empty);

                    player.openInventory(inventory);
                }
                else {
                    player.sendMessage(CCPlugin.getPrefix() + "Bitte warte, bis du aus FFA raus bist");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                }
            }
            else {
                sender.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
            }
        }

        return true;
    }
}
