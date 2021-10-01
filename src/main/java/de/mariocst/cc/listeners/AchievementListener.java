package de.mariocst.cc.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class AchievementListener implements Listener {
    @EventHandler
    public void onAchievement(PlayerAdvancementDoneEvent event) {
        event.message(Component.text(""));
    }
}
