package de.mariocst.cc.commands.Invsee;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class InvseeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            CCPlugin.getInstance().log("Bitte führe den Command InGame aus!");
            return false;
        }

        if (player.hasPermission("mario.invsee") || player.hasPermission("*") || player.isOp()) {
            String usage = "§cUsage: §6/invsee <Spieler>";
            try {
                if (args.length == 1) {
                    String nullp = "§cDieser Spieler existiert nicht!";
                    try {
                        Player t = player.getServer().getPlayer(args[0]);
                        if (t != null) {
                            if (t.getName().equals(player.getName())) {
                                sender.sendMessage(CCPlugin.getPrefix() + "Du kannst doch wohl noch dein eigenes Inventar öffnen xD");
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                            }
                            else {
                                player.openInventory(t.getInventory());
                            }
                        }
                        else {
                            sender.sendMessage(CCPlugin.getPrefix() + nullp);
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        sender.sendMessage(CCPlugin.getPrefix() + nullp);
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                    }
                }
                else {
                    sender.sendMessage(CCPlugin.getPrefix() + usage);
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(CCPlugin.getPrefix() + usage);
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }
}
