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

            switch (event.getCause()) {
                case KICK_COMMAND -> CCPlugin.getInstance().getServer().broadcast(Component.text(CCPlugin.getPrefix() + "Der Spieler §a" + player.getName() + " §fwurde für §a" + component.content() + " §fgekickt!"));
                case BANNED -> CCPlugin.getInstance().getServer().broadcast(Component.text(CCPlugin.getPrefix() + "Der Spieler §a" + player.getName() + " §fwurde für §a" + component.content() + " §fgebannt!"));
                case IP_BANNED -> CCPlugin.getInstance().getServer().broadcast(Component.text(CCPlugin.getPrefix() + "Der Spieler §a" + player.getName() + " §fwurde für §a" + component.content() + " §fIP gebannt!"));
                case FLYING_PLAYER -> CCPlugin.getInstance().getServer().broadcast(Component.text(CCPlugin.getPrefix() + "Der Spieler §a" + player.getName() + " §fwurde für fliegen gekickt!"));
            }
        }
        catch (ClassCastException ignored) { }
    }
}
