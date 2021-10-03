package de.mariocst.cc.listeners;

import de.mariocst.cc.CCPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class KickListener implements Listener {
    @EventHandler
    public void onKick(PlayerKickEvent event) {
        Player player = event.getPlayer();

        try {
            TextComponent component = (TextComponent) event.reason();

            CCPlugin.getInstance().getServer().broadcast(Component.text(CCPlugin.getPrefix() + "Der Spieler §a" + player.getName() + " §fwurde für §a" + component.content() + " §fgekickt!"));
        }
        catch (ClassCastException ignored) { }
    }
}
