package de.mariocst.cc.commands.Storing;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.backpack.BackpackStored;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BackpackStoredCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(CCPlugin.getPrefix() + "Dieser Command geht nur InGame!");
            return true;
        }

        if (player.hasPermission("mario.backpackstored") || player.hasPermission("mario.*") || player.isOp()) {
            try {
                if (args.length == 1) {
                    Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                    try {
                        if (t != null) {
                            BackpackStored backpackStored = CCPlugin.getInstance().getBackpackManagerStored().getBackpackStored(t.getUniqueId());
                            player.openInventory(backpackStored.getInventory());
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        player.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler existiert nicht!");
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                player.sendMessage(CCPlugin.getPrefix() + "/backpacksstored <Spieler>");
            }
        }
        else {
            sender.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return false;
    }
}
