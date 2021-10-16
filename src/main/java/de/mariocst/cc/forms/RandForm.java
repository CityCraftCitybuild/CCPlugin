package de.mariocst.cc.forms;

import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.util.PatternUtil;
import com.sk89q.worldedit.function.pattern.Pattern;
import de.mariocst.cc.CCPlugin;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.response.SimpleFormResponse;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;

public class RandForm {
    public void openRandForm(Player player) {
        SimpleForm simpleForm = SimpleForm.builder()
                .title("§6Rand")
                .button("§7Glatte Stein Stufe\n§8Spieler", FormImage.of(FormImage.Type.PATH, "textures/blocks/stone_slab_top.png"))
                .button("§6Geschnitzter Kürbis\n§8Spieler", FormImage.of(FormImage.Type.PATH, "textures/blocks/pumpkin_face_off.png"))
                .button("§6Oranger Beton\n§6Premium", FormImage.of(FormImage.Type.PATH, "textures/blocks/concrete_orange.png"))
                .button("§eGold Block\n§eGold", FormImage.of(FormImage.Type.PATH, "textures/blocks/gold_block.png"))
                .button("§bDiamant Block\n§bDiamant", FormImage.of(FormImage.Type.PATH, "textures/blocks/diamond_block.png"))
                .button("§2Smaragd Block\n§2Smaragd", FormImage.of(FormImage.Type.PATH, "textures/blocks/emerald_block.png"))
                .button("§5Obsidian\n§5Obsidian", FormImage.of(FormImage.Type.PATH, "textures/blocks/obsidian.png"))
                .button("§1Lapis Block\n§3Prime", FormImage.of(FormImage.Type.PATH, "textures/blocks/lapis_block.png"))
                .button("§5Netherite Block\n§1Ultimate", FormImage.of(FormImage.Type.PATH, "textures/blocks/netherite_block.png"))
                .button("§cSchließen", FormImage.of(FormImage.Type.PATH, "textures/blocks/barrier.png"))
                .responseHandler((form, responseData) -> {
                    SimpleFormResponse response = form.parseResponse(responseData);

                    if (response.getClickedButton() == null) return;

                    PlotPlayer<Player> plotPlayer = PlotPlayer.from(player);
                    Plot plot = plotPlayer.getCurrentPlot();

                    if (response.getClickedButton().getText().equals("§7Glatte Stein Stufe\n§8Spieler")) {
                        for (Plot plots : plot.getConnectedPlots()) {
                            Pattern pattern = PatternUtil.parse(plotPlayer, "smooth_stone_slab", false);

                            plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                        }

                        player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zur glatten Stein Stufe geändert!");
                    }
                    else if (response.getClickedButton().getText().equals("§6Geschnitzter Kürbis\n§8Spieler")) {
                        for (Plot plots : plot.getConnectedPlots()) {
                            Pattern pattern = PatternUtil.parse(plotPlayer, "carved_pumpkin", false);

                            plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                        }

                        player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zum geschnitzten Kürbis geändert!");
                    }
                    else if (response.getClickedButton().getText().equals("§6Oranger Beton\n§6Premium")) {
                        if (!player.hasPermission("mario.rand.premium") && !player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) {
                            player.sendMessage(CCPlugin.getPrefix() + "Du hast keine Rechte auf den orangen Beton!");
                            player.closeInventory();
                            return;
                        }

                        for (Plot plots : plot.getConnectedPlots()) {
                            Pattern pattern = PatternUtil.parse(plotPlayer, "orange_concrete", false);

                            plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                        }

                        player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zum orangen Beton geändert!");
                    }
                    else if (response.getClickedButton().getText().equals("§eGold Block\n§eGold")) {
                        if (!player.hasPermission("mario.rand.gold") && !player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) {
                            player.sendMessage(CCPlugin.getPrefix() + "Du hast keine Rechte auf den Gold Block!");
                            player.closeInventory();
                            return;
                        }

                        for (Plot plots : plot.getConnectedPlots()) {
                            Pattern pattern = PatternUtil.parse(plotPlayer, "gold_block", false);

                            plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                        }

                        player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zum Gold Block geändert!");
                    }
                    else if (response.getClickedButton().getText().equals("§bDiamant Block\n§bDiamant")) {
                        if (!player.hasPermission("mario.rand.diamant") && !player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) {
                            player.sendMessage(CCPlugin.getPrefix() + "Du hast keine Rechte auf den Diamant Block!");
                            player.closeInventory();
                            return;
                        }

                        for (Plot plots : plot.getConnectedPlots()) {
                            Pattern pattern = PatternUtil.parse(plotPlayer, "diamond_block", false);

                            plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                        }

                        player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zum Diamant Block geändert!");
                    }
                    else if (response.getClickedButton().getText().equals("§2Smaragd Block\n§2Smaragd")) {
                        if (!player.hasPermission("mario.rand.smaragd") && !player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) {
                            player.sendMessage(CCPlugin.getPrefix() + "Du hast keine Rechte auf den Smaragd Block!");
                            player.closeInventory();
                            return;
                        }

                        for (Plot plots : plot.getConnectedPlots()) {
                            Pattern pattern = PatternUtil.parse(plotPlayer, "emerald_block", false);

                            plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                        }

                        player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zum Smaragd Block geändert!");
                    }
                    else if (response.getClickedButton().getText().equals("§5Obsidian\n§5Obsidian")) {
                        if (!player.hasPermission("mario.rand.obsidian") && !player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) {
                            player.sendMessage(CCPlugin.getPrefix() + "Du hast keine Rechte auf Obsidian!");
                            player.closeInventory();
                            return;
                        }

                        for (Plot plots : plot.getConnectedPlots()) {
                            Pattern pattern = PatternUtil.parse(plotPlayer, "obsidian", false);

                            plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                        }

                        player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zu Obsidian geändert!");
                    }
                    else if (response.getClickedButton().getText().equals("§1Lapis Block\n§3Prime")) {
                        if (!player.hasPermission("mario.rand.prime") && !player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) {
                            player.sendMessage(CCPlugin.getPrefix() + "Du hast keine Rechte auf den Lapis Block!");
                            player.closeInventory();
                            return;
                        }

                        for (Plot plots : plot.getConnectedPlots()) {
                            Pattern pattern = PatternUtil.parse(plotPlayer, "lapis_block", false);

                            plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                        }

                        player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zum Lapis Block geändert!");
                    }
                    else if (response.getClickedButton().getText().equals("§5Netherite Block\n§1Ultimate")) {
                        if (!player.hasPermission("mario.rand.ultimate") && !player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) {
                            player.sendMessage(CCPlugin.getPrefix() + "Du hast keine Rechte auf den Netherite Block!");
                            player.closeInventory();
                            return;
                        }

                        for (Plot plots : plot.getConnectedPlots()) {
                            Pattern pattern = PatternUtil.parse(plotPlayer, "netherite_block", false);

                            plots.getPlotModificationManager().setComponent("border", pattern, plotPlayer, null);
                        }

                        player.sendMessage(CCPlugin.getPrefix() + "Du hast deinen Rand zum Netherite Block geändert!");
                    }
                }).build();

        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());

        floodgatePlayer.sendForm(simpleForm);
    }
}
