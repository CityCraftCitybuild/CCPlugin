package de.mariocst.cc.commands.Send;

import de.mariocst.cc.CCPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class SendTitleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if(!(sender instanceof Player player)) {
            try {
                if (args.length >= 2) {
                    Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                    try {
                        if (t != null) {
                            StringBuilder msg = new StringBuilder();
                            for(int i = 1; i < args.length; i++) {
                                msg.append(args[i]).append(" ");
                            }

                            t.showTitle(Title.title(Component.text(""), Component.text(msg.toString().replaceAll("&", "§")), Title.Times.of(Duration.ofMillis(100L), Duration.ofSeconds(1L), Duration.ofMillis(100L))));
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        sender.sendMessage("Dieser Spieler existiert nicht!");
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage("/st <Spieler> <Titel>");
            }
            return true;
        }

        if(player.hasPermission("mario.sendtitle") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length >= 2) {
                    Player t = player.getServer().getPlayer(args[0]);

                    try {
                        if (t != null) {
                            StringBuilder msg = new StringBuilder();
                            for(int i = 1; i < args.length; i++) {
                                msg.append(args[i]).append(" ");
                            }

                            t.showTitle(Title.title(Component.text(""), Component.text(msg.toString().replaceAll("&", "§")), Title.Times.of(Duration.ofMillis(100L), Duration.ofSeconds(1L), Duration.ofMillis(100L))));
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        player.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(CCPlugin.getPrefix() + "/st <Spieler> <Titel>");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
            }
        } else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return true;
    }
}
