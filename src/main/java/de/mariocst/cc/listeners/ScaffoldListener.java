package de.mariocst.cc.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class ScaffoldListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("mario.scaffold") && !player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) return;

        if (player.getInventory().getItemInMainHand().getType() != Material.WHITE_WOOL || !player.getInventory().getItemInMainHand().hasItemMeta()) return;

        if (!Objects.equals(player.getInventory().getItemInMainHand().getItemMeta().displayName(), Component.text("§cScaffold Wool"))) return;

        if (player.getWorld().getBlockAt(player.getLocation().add(0.0, -1.0, 0.0)).getType() != Material.AIR) return;

        player.swingMainHand();
        player.getWorld().getBlockAt(player.getLocation().add(0.0, -1.0, 0.0)).setType(Material.WHITE_WOOL);
    }
}
