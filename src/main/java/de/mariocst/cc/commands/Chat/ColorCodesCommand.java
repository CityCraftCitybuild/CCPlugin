package de.mariocst.cc.commands.Chat;

import de.mariocst.cc.CCPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ColorCodesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CCPlugin.getPrefix() + "§6ColorCodes:");
            sender.sendMessage(CCPlugin.getPrefix() + "§6Alle beginnen mit & !");
            sender.sendMessage(CCPlugin.getPrefix() + "0: §0Schwarz§f, 1: §1Dunkel Blau§f, 2: §2Dunkel Grün§f, 3: §3Cyan§f, 4: §4Rot");
            sender.sendMessage(CCPlugin.getPrefix() + "5: §5Lila§f, 6: §6Orange§f, 7: §7Hell Grau§f, 8: §8Dunkel Grau§f, 9: §9Hell Blau");
            sender.sendMessage(CCPlugin.getPrefix() + "a: §aHell Grün§f, b: §bHell Blau§f, c: §cHell Rot§f, d: §dPink§f, e: §eGelb§f, f: Weiß");
            sender.sendMessage(CCPlugin.getPrefix() + "k: §kmagische Zeichen§r§f, l: §lFett§r§f, o: §oKursiv§r§f, r: §rMacht alles rückgängig");
            return true;
        }

        Player player = (Player) sender;

        if(player.hasPermission("mario.colorcodes") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            sender.sendMessage(CCPlugin.getPrefix() + "§6ColorCodes:");
            sender.sendMessage(CCPlugin.getPrefix() + "§6Alle beginnen mit & !");
            sender.sendMessage(CCPlugin.getPrefix() + "0: §0Schwarz§f, 1: §1Dunkel Blau§f, 2: §2Dunkel Grün§f, 3: §3Cyan§f, 4: §4Rot");
            sender.sendMessage(CCPlugin.getPrefix() + "5: §5Lila§f, 6: §6Orange§f, 7: §7Hell Grau§f, 8: §8Dunkel Grau§f, 9: §9Hell Blau");
            sender.sendMessage(CCPlugin.getPrefix() + "a: §aHell Grün§f, b: §bHell Blau§f, c: §cHell Rot§f, d: §dPink§f, e: §eGelb§f, f: Weiß");
            sender.sendMessage(CCPlugin.getPrefix() + "k: §kmagische Zeichen§r§f, l: §lFett§r§f, o: §oKursiv§r§f, r: §rMacht alles rückgängig");
        } else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return true;
    }
}
