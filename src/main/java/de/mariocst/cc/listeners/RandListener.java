package de.mariocst.cc.listeners;

import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.util.PatternUtil;
import com.sk89q.worldedit.function.pattern.Pattern;
import de.mariocst.cc.CCPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.Objects;

public class RandListener implements Listener {
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if (player.getOpenInventory().title().equals(Component.text("§cRand"))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (player.getOpenInventory().title().equals(Component.text("§cRand"))) {
            if (event.getCurrentItem() == null || event.getClickedInventory() == player.getInventory()) return;

            event.setCancelled(true);

            PlotPlayer<Player> plotPlayer = PlotPlayer.from(player);
            Plot plot = plotPlayer.getCurrentPlot();

            if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§7Glatte Stein Stufe"))) {
                for (Plot plots : plot.getConnectedPlots()) {
                    Pattern pattern = PatternUtil.parse(plotPlayer, "smooth_stone_slab", false);

                    plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                }

                player.closeInventory();

                player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zur glatten Stein Stufe geändert!");
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§6Geschnitzter Kürbis"))) {
                for (Plot plots : plot.getConnectedPlots()) {
                    Pattern pattern = PatternUtil.parse(plotPlayer, "carved_pumpkin", false);

                    plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                }

                player.closeInventory();

                player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zum geschnitzten Kürbis geändert!");
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§6Oranger Beton"))) {
                if (!player.hasPermission("mario.rand.premium") && !player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) {
                    player.sendMessage(CCPlugin.getPrefix() + "Du hast keine Rechte auf den orangen Beton!");
                    player.closeInventory();
                    return;
                }

                for (Plot plots : plot.getConnectedPlots()) {
                    Pattern pattern = PatternUtil.parse(plotPlayer, "orange_concrete", false);

                    plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                }

                player.closeInventory();

                player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zum orangen Beton geändert!");
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§eGold Block"))) {
                if (!player.hasPermission("mario.rand.gold") && !player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) {
                    player.sendMessage(CCPlugin.getPrefix() + "Du hast keine Rechte auf den Gold Block!");
                    player.closeInventory();
                    return;
                }

                for (Plot plots : plot.getConnectedPlots()) {
                    Pattern pattern = PatternUtil.parse(plotPlayer, "gold_block", false);

                    plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                }

                player.closeInventory();

                player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zum Gold Block geändert!");
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§bDiamant Block"))) {
                if (!player.hasPermission("mario.rand.diamant") && !player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) {
                    player.sendMessage(CCPlugin.getPrefix() + "Du hast keine Rechte auf den Diamant Block!");
                    player.closeInventory();
                    return;
                }

                for (Plot plots : plot.getConnectedPlots()) {
                    Pattern pattern = PatternUtil.parse(plotPlayer, "diamond_block", false);

                    plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                }

                player.closeInventory();

                player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zum Diamant Block geändert!");
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§2Smaragd Block"))) {
                if (!player.hasPermission("mario.rand.smaragd") && !player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) {
                    player.sendMessage(CCPlugin.getPrefix() + "Du hast keine Rechte auf den Smaragd Block!");
                    player.closeInventory();
                    return;
                }

                for (Plot plots : plot.getConnectedPlots()) {
                    Pattern pattern = PatternUtil.parse(plotPlayer, "emerald_block", false);

                    plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                }

                player.closeInventory();

                player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zum Smaragd Block geändert!");
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§5Obsidian"))) {
                if (!player.hasPermission("mario.rand.obsidian") && !player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) {
                    player.sendMessage(CCPlugin.getPrefix() + "Du hast keine Rechte auf Obsidian!");
                    player.closeInventory();
                    return;
                }

                for (Plot plots : plot.getConnectedPlots()) {
                    Pattern pattern = PatternUtil.parse(plotPlayer, "obsidian", false);

                    plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                }

                player.closeInventory();

                player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zu Obsidian geändert!");
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§1Lapis Block"))) {
                if (!player.hasPermission("mario.rand.prime") && !player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) {
                    player.sendMessage(CCPlugin.getPrefix() + "Du hast keine Rechte auf den Lapis Block!");
                    player.closeInventory();
                    return;
                }

                for (Plot plots : plot.getConnectedPlots()) {
                    Pattern pattern = PatternUtil.parse(plotPlayer, "lapis_block", false);

                    plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                }

                player.closeInventory();

                player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zum Lapis Block geändert!");
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§5Netherite Block"))) {
                if (!player.hasPermission("mario.rand.ultimate") && !player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) {
                    player.sendMessage(CCPlugin.getPrefix() + "Du hast keine Rechte auf den Netherite Block!");
                    player.closeInventory();
                    return;
                }

                for (Plot plots : plot.getConnectedPlots()) {
                    Pattern pattern = PatternUtil.parse(plotPlayer, "netherite_block", false);

                    plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                }

                player.closeInventory();

                player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zum Netherite Block geändert!");
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§cSchließen"))) {
                player.closeInventory();
            }
        }
    }
}
