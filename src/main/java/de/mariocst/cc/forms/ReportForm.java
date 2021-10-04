package de.mariocst.cc.forms;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.DiscordConfigData;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.CustomForm;
import org.geysermc.cumulus.response.CustomFormResponse;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;

import java.io.IOException;
import java.util.Objects;

public class ReportForm {
    public void openReport(Player player) {
        CustomForm customForm = CustomForm.builder()
                .title("§cReport")
                .input("§4Spieler", player.getName())
                .input("§6Grund", "Hacking")
                .responseHandler((form, responseData) -> {
                    CustomFormResponse response = form.parseResponse(responseData);

                    if (Objects.requireNonNull(response.getInput(0)).isEmpty() || Objects.requireNonNull(response.getInput(1)).isEmpty()) {
                        player.sendMessage(CCPlugin.getPrefix() + "Bitte fülle alle Felder aus!");
                        return;
                    }

                    try {
                        Player t = player.getServer().getPlayer(Objects.requireNonNull(response.getInput(0)));

                        OfflinePlayer oT = null;

                        for (OfflinePlayer offlinePlayer : CCPlugin.getInstance().getServer().getOfflinePlayers()) {
                            if (Objects.equals(offlinePlayer.getName(), response.getInput(0))) {
                                oT = offlinePlayer;
                            }
                        }

                        if (t != null) {
                            int staffOnline = 0;

                            for (Player staff : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                                if (staff.hasPermission("mario.staff") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                                    staffOnline++;

                                    staff.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + player.getName() + " hat §a" + t.getName() + " §ffür §a" + response.getInput(1) + " §freportet!");
                                }
                            }

                            player.sendMessage(CCPlugin.getPrefix() + "Du hast den Spieler §a" + t.getName() + " §ffür §a" + response.getInput(1) + " §ferfolgreich reportet!");

                            if (staffOnline == 0) player.sendMessage(CCPlugin.getPrefix() + "§cEs ist kein Teammitglied Online!");

                            if (!DiscordConfigData.getDiscordConfigData().getUrl().equals("")) {
                                try {
                                    CCPlugin.getInstance().sendReport(player, t, response.getInput(1));
                                    player.sendMessage(CCPlugin.getPrefix() + "Dein Report wurde erfolgreich an das Discord Team gesendet!");
                                }
                                catch (IOException e) {
                                    player.sendMessage(CCPlugin.getPrefix() + "Dein Report konnte leider nicht an das Discord Team gesendet werden, weil ein Fehler aufgetreten ist.");
                                }
                            }
                        }
                        else if (oT != null) {
                            int staffOnline = 0;

                            for (Player staff : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                                if (staff.hasPermission("mario.staff") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                                    staffOnline++;

                                    staff.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + player.getName() + " hat §a" + oT.getName() + " §ffür §a" + response.getInput(1) + " §freportet!");
                                }
                            }

                            player.sendMessage(CCPlugin.getPrefix() + "Du hast den Spieler §a" + oT.getName() + " §ffür §a" + response.getInput(1) + " §ferfolgreich reportet!");

                            if (staffOnline == 0)  player.sendMessage(CCPlugin.getPrefix() + "§cEs ist kein Teammitglied Online!");

                            if (!DiscordConfigData.getDiscordConfigData().getUrl().equals("")) {
                                try {
                                    CCPlugin.getInstance().sendReport(player, oT, response.getInput(1));
                                    player.sendMessage(CCPlugin.getPrefix() + "Dein Report wurde erfolgreich an das Discord Team gesendet!");
                                }
                                catch (IOException e) {
                                    player.sendMessage(CCPlugin.getPrefix() + "Dein Report konnte leider nicht an das Discord Team gesendet werden, weil ein Fehler aufgetreten ist.");
                                }
                            }
                        }
                        else {
                            player.sendMessage("§cDieser Spieler existiert nicht!");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                        }
                    }
                    catch (NullPointerException e) {
                        player.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + response.getInput(0) + " existiert nicht!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                    }
                })
                .build();

        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());

        floodgatePlayer.sendForm(customForm);
    }
}
