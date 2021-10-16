package de.mariocst.cc.commands.Plot;

import com.plotsquared.core.player.PlotPlayer;
import de.mariocst.cc.CCPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.geysermc.floodgate.api.FloodgateApi;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RandCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            CCPlugin.getInstance().log("Dieser Command kann leider nur InGame ausgeführt werden!");
            return true;
        }

        if (player.hasPermission("mario.rand") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            PlotPlayer<Player> plotPlayer = PlotPlayer.from(player);

            if (plotPlayer.getCurrentPlot() == null) {
                player.sendMessage(CCPlugin.getPrefix() + "Du befindest dich nicht auf einem Plot!");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                return true;
            }

            if (!player.hasPermission("mario.rand.bypass") && !player.hasPermission("mario.*") && !player.hasPermission("*") && player.isOp()) {
                if (!plotPlayer.getCurrentPlot().isOwner(player.getUniqueId())) {
                    player.sendMessage(CCPlugin.getPrefix() + "Du bist nicht der Owner des Plots!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    return true;
                }
            }

            if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
                CCPlugin.getInstance().getRandForm().openRandForm(player);
                return true;
            }

            player.closeInventory();

            Inventory inventory = CCPlugin.getInstance().getServer().createInventory(player, 18, Component.text("§cRand"));

            ItemStack smoothStoneSlab = new ItemStack(Material.SMOOTH_STONE_SLAB, 1);
            ItemMeta smoothStoneSlabMeta = smoothStoneSlab.getItemMeta();

            ItemStack carvedPumpkin = new ItemStack(Material.CARVED_PUMPKIN, 1);
            ItemMeta carvedPumpkinMeta = carvedPumpkin.getItemMeta();

            ItemStack orangeConcrete = new ItemStack(Material.ORANGE_CONCRETE, 1);
            ItemMeta orangeConcreteMeta = orangeConcrete.getItemMeta();

            ItemStack goldBlock = new ItemStack(Material.GOLD_BLOCK, 1);
            ItemMeta goldBlockMeta = goldBlock.getItemMeta();

            ItemStack diamondBlock = new ItemStack(Material.DIAMOND_BLOCK, 1);
            ItemMeta diamondBlockMeta = diamondBlock.getItemMeta();

            ItemStack emeraldBlock = new ItemStack(Material.EMERALD_BLOCK, 1);
            ItemMeta emeraldBlockMeta = emeraldBlock.getItemMeta();

            ItemStack obsidianBlock = new ItemStack(Material.OBSIDIAN, 1);
            ItemMeta obsidianBlockMeta = obsidianBlock.getItemMeta();

            ItemStack lapisBlock = new ItemStack(Material.LAPIS_BLOCK, 1);
            ItemMeta lapisBlockMeta = lapisBlock.getItemMeta();

            ItemStack netheriteBlock = new ItemStack(Material.NETHERITE_BLOCK, 1);
            ItemMeta netheriteBlockMeta = netheriteBlock.getItemMeta();

            ItemStack close = new ItemStack(Material.BARRIER, 1);
            ItemMeta closeMeta = close.getItemMeta();

            ItemStack redGlassPane = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
            ItemMeta redGlassPaneMeta = redGlassPane.getItemMeta();

            List<Component> spieler = new ArrayList<>();
            spieler.add(Component.text("§8Spieler"));

            List<Component> premium = new ArrayList<>();
            premium.add(Component.text("§6Premium"));

            List<Component> gold = new ArrayList<>();
            gold.add(Component.text("§eGold"));

            List<Component> diamond = new ArrayList<>();
            diamond.add(Component.text("§bDiamant"));

            List<Component> emerald = new ArrayList<>();
            emerald.add(Component.text("§2Smaragd"));

            List<Component> obsidian = new ArrayList<>();
            obsidian.add(Component.text("§5Obsidian"));

            List<Component> prime = new ArrayList<>();
            prime.add(Component.text("§3Prime"));

            List<Component> ultimate = new ArrayList<>();
            ultimate.add(Component.text("§1Ultimate"));

            smoothStoneSlabMeta.displayName(Component.text("§7Glatte Stein Stufe"));
            smoothStoneSlabMeta.lore(spieler);
            smoothStoneSlab.setItemMeta(smoothStoneSlabMeta);

            carvedPumpkinMeta.displayName(Component.text("§6Geschnitzter Kürbis"));
            carvedPumpkinMeta.lore(spieler);
            carvedPumpkin.setItemMeta(carvedPumpkinMeta);

            orangeConcreteMeta.displayName(Component.text("§6Oranger Beton"));
            orangeConcreteMeta.lore(premium);
            orangeConcrete.setItemMeta(orangeConcreteMeta);

            goldBlockMeta.displayName(Component.text("§eGold Block"));
            goldBlockMeta.lore(gold);
            goldBlock.setItemMeta(goldBlockMeta);

            diamondBlockMeta.displayName(Component.text("§bDiamant Block"));
            diamondBlockMeta.lore(diamond);
            diamondBlock.setItemMeta(diamondBlockMeta);

            emeraldBlockMeta.displayName(Component.text("§2Smaragd Block"));
            emeraldBlockMeta.lore(emerald);
            emeraldBlock.setItemMeta(emeraldBlockMeta);

            obsidianBlockMeta.displayName(Component.text("§5Obsidian"));
            obsidianBlockMeta.lore(obsidian);
            obsidianBlock.setItemMeta(obsidianBlockMeta);

            lapisBlockMeta.displayName(Component.text("§1Lapis Block"));
            lapisBlockMeta.lore(prime);
            lapisBlock.setItemMeta(lapisBlockMeta);

            netheriteBlockMeta.displayName(Component.text("§5Netherite Block"));
            netheriteBlockMeta.lore(ultimate);
            netheriteBlock.setItemMeta(netheriteBlockMeta);

            closeMeta.displayName(Component.text("§cSchließen"));
            close.setItemMeta(closeMeta);

            redGlassPaneMeta.displayName(Component.text(""));
            redGlassPane.setItemMeta(redGlassPaneMeta);

            inventory.setItem(0, smoothStoneSlab);
            inventory.setItem(1, carvedPumpkin);
            inventory.setItem(2, orangeConcrete);
            inventory.setItem(3, goldBlock);
            inventory.setItem(4, diamondBlock);
            inventory.setItem(5, emeraldBlock);
            inventory.setItem(6, obsidianBlock);
            inventory.setItem(7, lapisBlock);
            inventory.setItem(8, netheriteBlock);
            inventory.setItem(17, close);

            for (int i = 0; i <= 17; i++) {
                if (inventory.getItem(i) == null) inventory.setItem(i, redGlassPane);
            }

            player.openInventory(inventory);
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }
}
