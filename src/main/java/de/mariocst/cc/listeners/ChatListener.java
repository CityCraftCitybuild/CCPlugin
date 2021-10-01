package de.mariocst.cc.listeners;

import de.mariocst.cc.CCPlugin;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.BroadcastMessageEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onStaffChat(AsyncChatEvent event) {
        Player player = event.getPlayer();

        if (CCPlugin.getInstance().staffChat.contains(player)) {
            event.setCancelled(true);

            CCPlugin.getInstance().log(CCPlugin.getInstance().getStaffChatPrefix().getPlayerPrefix(player) + event.message().toString().replaceAll("&", "§"));

            for (Player staff : CCPlugin.getInstance().getServer().getOnlinePlayers()) {
                if (staff.hasPermission("mario.staff") || staff.hasPermission("mario.*") || staff.hasPermission("*") || staff.isOp()) {
                    staff.sendMessage(CCPlugin.getInstance().getStaffChatPrefix().getPlayerPrefix(player) + event.message().toString().replaceAll("&", "§"));
                }
            }
        }
    }

    @EventHandler
    public void onNothingChat(BroadcastMessageEvent event) {
        TextComponent component = (TextComponent) event.message();

        if (component.toString().equals("")) event.setCancelled(true);
    }
}
