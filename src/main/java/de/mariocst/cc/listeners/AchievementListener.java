package de.mariocst.cc.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class AchievementListener implements Listener {
    @EventHandler
    public void onAchievement(PlayerAdvancementDoneEvent event) {
        event.message(null);
    }
}
