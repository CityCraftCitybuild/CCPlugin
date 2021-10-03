package de.mariocst.cc.listeners;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ServerListPingListener implements Listener {
    @EventHandler
    public void onPing(PaperServerListPingEvent event) {
        event.setVersion("Minecraft 1.9.x - 1.17.x");
    }
}
