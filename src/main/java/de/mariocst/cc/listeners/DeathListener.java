package de.mariocst.cc.listeners;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configdata.FFAData;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (player.getWorld().getName().equalsIgnoreCase(FFAData.getFFAData().getWorldName()) && CCPlugin.getInstance().getServer().getWorld(FFAData.getFFAData().getWorldName()) != null) {
            player.getInventory().remove(Material.ARROW);

            player.getInventory().addItem(new ItemStack(Material.ARROW, 16));
        }
    }

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            Player died = event.getEntity();
            Player killer = died.getKiller();

            if (died.getWorld().getName().equalsIgnoreCase(FFAData.getFFAData().getWorldName()) && killer.getWorld().getName().equalsIgnoreCase(FFAData.getFFAData().getWorldName())
            && CCPlugin.getInstance().getServer().getWorld(FFAData.getFFAData().getWorldName()) != null) {
                CCPlugin.getInstance().getServer().broadcast(Component.text(CCPlugin.getPrefix() + died.getName() + " wurde von " + killer.getName() + " gekillt!"));
                died.sendMessage(CCPlugin.getPrefix() + "Der Spieler " + killer.getName() + " hatte noch " + (Math.floor(killer.getHealth()) / 2) + " Herzen!");
                killer.setHealth(20.0D);
            }
        }
    }
}
