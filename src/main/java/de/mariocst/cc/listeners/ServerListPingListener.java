package de.mariocst.cc.listeners;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.utils.FakePlayerProfile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ServerListPingListener implements Listener {
    @EventHandler
    public void onPing(PaperServerListPingEvent event) {
        event.setVersion("Minecraft 1.9.x - 1.17.x");

        int players = 0;

        for (Player ignored : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
            players++;
        }

        event.setNumPlayers(players);

        event.getPlayerSample().clear();
        event.getPlayerSample().add(new FakePlayerProfile("\u00A73\u00A7lCity\u00A79\u00A7lCraft"));
        event.getPlayerSample().add(new FakePlayerProfile("\u00A7b\u00A7l1.9.x - 1.17.x"));
    }
}
