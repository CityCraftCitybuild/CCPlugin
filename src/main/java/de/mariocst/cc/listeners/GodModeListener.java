package de.mariocst.cc.listeners;

import de.mariocst.cc.CCPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class GodModeListener implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (CCPlugin.getInstance().godMode.contains(player)) event.setCancelled(true);
    }

    @EventHandler
    public void onHealMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (CCPlugin.getInstance().godMode.contains(player)) {
            if (player.getHealth() < 20.0) player.setHealth(20.0);
            if (player.getFoodLevel() < 20) player.setFoodLevel(20);
        }
    }
}
