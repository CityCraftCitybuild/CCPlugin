package de.mariocst.cc.forms;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.response.SimpleFormResponse;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;

public class NavigatorForm {
    public void openNavigator(Player player) {
        SimpleForm simpleForm = SimpleForm.builder()
                .title("§6Navigator")
                .button("§1Lobby", FormImage.of(FormImage.Type.PATH, "textures/blocks/beacon.png"))
                .button("§3CityBuild", FormImage.of(FormImage.Type.PATH, "textures/blocks/quartz_block_top.png"))
                .button("§2Farmwelt", FormImage.of(FormImage.Type.PATH, "textures/blocks/grass_side_carried.png"))
                .button("§4Nether", FormImage.of(FormImage.Type.PATH, "textures/blocks/netherrack.png"))
                .button("§eEnd", FormImage.of(FormImage.Type.PATH, "textures/blocks/end_stone.png"))
                .button("§bFFA", FormImage.of(FormImage.Type.PATH, "textures/items/iron_sword.png"))
                .button("§cSchließen", FormImage.of(FormImage.Type.PATH, "textures/blocks/barrier.png"))
                .responseHandler((form, responseData) -> {
                    SimpleFormResponse response = form.parseResponse(responseData);

                    if (response.getClickedButton() == null) return;

                    if (response.getClickedButton().getText().equals("§1Lobby")) {
                        LobbyData lobbyData = LobbyData.getLobbyData();

                        if (CCPlugin.getInstance().getServer().getWorld(lobbyData.getWorldName()) != null) {
                            player.teleport(new Location(
                                    CCPlugin.getInstance().getServer().getWorld(lobbyData.getWorldName()),
                                    lobbyData.getX(),
                                    lobbyData.getY(),
                                    lobbyData.getZ(),
                                    lobbyData.getYaw(),
                                    lobbyData.getPitch()));
                        }
                        else {
                            if (player.isOp()) {
                                player.sendMessage(CCPlugin.getPrefix() + "Es gibt aus irgendeinem Grund die Welt mit dem Namen \"" + lobbyData.getWorldName() + "\" nicht...");
                            }
                        }
                    }
                    else if (response.getClickedButton().getText().equals("§3CityBuild")) {
                        CB01Data cb01Data = CB01Data.getCB01Data();

                        if (CCPlugin.getInstance().getServer().getWorld(cb01Data.getWorldName()) != null) {
                            player.teleport(new Location(
                                    CCPlugin.getInstance().getServer().getWorld(cb01Data.getWorldName()),
                                    cb01Data.getX(),
                                    cb01Data.getY(),
                                    cb01Data.getZ(),
                                    cb01Data.getYaw(),
                                    cb01Data.getPitch()));
                        }
                        else {
                            if (player.isOp()) {
                                player.sendMessage(CCPlugin.getPrefix() + "Es gibt die Welt mit dem Namen \"" + cb01Data.getWorldName() + "\" nicht...");
                            }
                        }
                    }
                    else if (response.getClickedButton().getText().equals("§2Farmwelt")) {
                        FarmweltData farmweltData = FarmweltData.getFarmweltData();

                        if (CCPlugin.getInstance().getServer().getWorld(farmweltData.getWorldName()) != null) {
                            player.teleport(new Location(
                                    CCPlugin.getInstance().getServer().getWorld(farmweltData.getWorldName()),
                                    farmweltData.getX(),
                                    farmweltData.getY(),
                                    farmweltData.getZ(),
                                    farmweltData.getYaw(),
                                    farmweltData.getPitch()));
                        }
                        else {
                            if (player.isOp()) {
                                player.sendMessage(CCPlugin.getPrefix() + "Es gibt aus irgendeinem Grund die Welt mit dem Namen \"" + farmweltData.getWorldName() + "\" nicht...");
                            }
                        }
                    }
                    else if (response.getClickedButton().getText().equals("§4Nether")) {
                        NetherData netherData = NetherData.getNetherData();

                        if (CCPlugin.getInstance().getServer().getWorld(netherData.getWorldName()) != null) {
                            player.teleport(new Location(
                                    CCPlugin.getInstance().getServer().getWorld(netherData.getWorldName()),
                                    netherData.getX(),
                                    netherData.getY(),
                                    netherData.getZ(),
                                    netherData.getYaw(),
                                    netherData.getPitch()));
                        }
                        else {
                            if (player.isOp()) {
                                player.sendMessage(CCPlugin.getPrefix() + "Es gibt aus irgendeinem Grund die Welt mit dem Namen \"" + netherData.getWorldName() + "\" nicht...");
                            }
                        }
                    }
                    else if (response.getClickedButton().getText().equals("§eEnd")) {
                        EndData endData = EndData.getEndData();

                        if (CCPlugin.getInstance().getServer().getWorld(endData.getWorldName()) != null) {
                            player.teleport(new Location(
                                    CCPlugin.getInstance().getServer().getWorld(endData.getWorldName()),
                                    endData.getX(),
                                    endData.getY(),
                                    endData.getZ(),
                                    endData.getYaw(),
                                    endData.getPitch()));
                        }
                        else {
                            if (player.isOp()) {
                                player.sendMessage(CCPlugin.getPrefix() + "Es gibt aus irgendeinem Grund die Welt mit dem Namen \"" + endData.getWorldName() + "\" nicht...");
                            }
                        }
                    }
                    else if (response.getClickedButton().getText().equals("§bFFA")) {
                        FFAData ffaData = FFAData.getFFAData();

                        if (CCPlugin.getInstance().getServer().getWorld(ffaData.getWorldName()) != null) {
                            player.teleport(new Location(
                                    CCPlugin.getInstance().getServer().getWorld(ffaData.getWorldName()),
                                    ffaData.getX(),
                                    ffaData.getY(),
                                    ffaData.getZ(),
                                    ffaData.getYaw(),
                                    ffaData.getPitch()));
                        }
                        else {
                            if (player.isOp()) {
                                player.sendMessage(CCPlugin.getPrefix() + "Es gibt aus irgendeinem Grund die Welt mit dem Namen \"" + ffaData.getWorldName() + "\" nicht...");
                            }
                        }
                    }
                }).build();

        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());

        floodgatePlayer.sendForm(simpleForm);
    }
}
