package de.mariocst.cc.listeners;

import de.mariocst.cc.CCPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class GodmodeListener implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();

        if (CCPlugin.getInstance().godmode.contains(player)) event.setCancelled(true);
    }

    @EventHandler
    public void onHealMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (CCPlugin.getInstance().godmode.contains(player)) {
            if (player.getHealth() < 20.0) player.setHealth(20.0);
            if (player.getFoodLevel() < 20) player.setFoodLevel(20);
        }
    }
}
