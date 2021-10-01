package de.mariocst.cc.commands.Player;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class WeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            CCPlugin.getInstance().log("Bitte führe den Command Ingame aus!");
            return true;
        }

        Player player = (Player) sender;

        if (player.hasPermission("mario.weed") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            for (PotionEffect effect : player.getActivePotionEffects()) {
                if (effect.getType() == PotionEffectType.CONFUSION || effect.getType() == PotionEffectType.SPEED) {
                    player.sendMessage(CCPlugin.getPrefix() + "Bitte lasse erst die Effekte auslaufen!");
                    return true;
                }
            }

            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 255, false, false, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 3, false, false, true));

            player.sendMessage(CCPlugin.getPrefix() + "Du hast geraucht.");
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "§cKeine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
        }
        return false;
    }
}
