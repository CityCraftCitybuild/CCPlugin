package de.mariocst.cc.commands.Storing;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.backpack.BackpackStored;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RestoreInventoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        final String usage = "/restoreinventory <Spieler>";

        if (!(sender instanceof Player player)) {
            try {
                if (args.length == 1) {
                    Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                    try {
                        if (t != null) {
                            if (!t.getWorld().getName().equalsIgnoreCase("ffa")) {
                                BackpackStored backpackStored = CCPlugin.getInstance().getBackpackManagerStored().getBackpackStored(t.getUniqueId());

                                int slots = 0;

                                for (int i = 0; i <= 36; i++) {
                                    if (backpackStored.getInventory().getItem(i) != null && t.getInventory().getItem(i) == null) {
                                        t.getInventory().setItem(i, backpackStored.getInventory().getItem(i));

                                        backpackStored.getInventory().clear(i);

                                        slots++;
                                    }
                                }

                                for (int i = 36; i <= 40; i++) {
                                    switch (i) {
                                        case 37 -> {
                                            if (backpackStored.getInventory().getItem(i) != null && t.getInventory().getHelmet() == null) {
                                                t.getInventory().setItem(103, backpackStored.getInventory().getItem(i));

                                                backpackStored.getInventory().clear(i);

                                                slots++;
                                            }
                                        }
                                        case 38 -> {
                                            if (backpackStored.getInventory().getItem(i) != null && t.getInventory().getChestplate() == null) {
                                                t.getInventory().setItem(102, backpackStored.getInventory().getItem(i));

                                                backpackStored.getInventory().clear(i);

                                                slots++;
                                            }
                                        }
                                        case 39 -> {
                                            if (backpackStored.getInventory().getItem(i) != null && t.getInventory().getLeggings() == null) {
                                                t.getInventory().setItem(101, backpackStored.getInventory().getItem(i));

                                                backpackStored.getInventory().clear(i);

                                                slots++;
                                            }
                                        }
                                        case 40 -> {
                                            if (backpackStored.getInventory().getItem(i) != null && t.getInventory().getBoots() == null) {
                                                t.getInventory().setItem(100, backpackStored.getInventory().getItem(i));

                                                backpackStored.getInventory().clear(i);

                                                slots++;
                                            }
                                        }
                                    }
                                }

                                if (backpackStored.getInventory().getItem(41) != null && t.getInventory().getItem(-106) == null) {
                                    backpackStored.getInventory().setItem(-106, backpackStored.getInventory().getItem(41));

                                    backpackStored.getInventory().clear(41);

                                    slots++;
                                }

                                if (slots == 0) {
                                    sender.sendMessage(CCPlugin.getPrefix() + "Das gelagerte Backpack ist leer");
                                }
                                else {
                                    int notEmptySlots = 0;

                                    for (int i = 0; i <= 41; i++) {
                                        if (backpackStored.getInventory().getItem(i) != null) {
                                            notEmptySlots++;
                                        }
                                    }

                                    if (notEmptySlots != 0) {
                                        sender.sendMessage(CCPlugin.getPrefix() + "Ein paar Items sind zurückgeblieben!");
                                        t.sendMessage(CCPlugin.getPrefix() + "Ein paar Items sind leider nicht zurück, weil du an den entsprechenden Slots Items hattest!");
                                    }

                                    sender.sendMessage(CCPlugin.getPrefix() + "Das Inventar von " + t.getName() + " wurde erfolgreich zurück gegeben!");
                                    t.sendMessage(CCPlugin.getPrefix() + "Dein Inventar wurde zurpck gegeben! Netter Admin haha");
                                }
                            }
                            else {
                                sender.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler ist gerade in FFA. Also bitte cheate im keine Items!");
                            }
                        }
                    }
                    catch (NullPointerException e) {
                        sender.sendMessage("Dieser Spieler existiert nicht!");
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(CCPlugin.getPrefix() + usage);
            }
            return true;
        }

        if (player.hasPermission("mario.restoreinventory") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length == 1) {
                    Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                    try {
                        if (t != null) {
                            if (!t.getWorld().getName().equalsIgnoreCase("ffa")) {
                                BackpackStored backpackStored = CCPlugin.getInstance().getBackpackManagerStored().getBackpackStored(t.getUniqueId());

                                int slots = 0;

                                for (int i = 0; i <= 36; i++) {
                                    if (backpackStored.getInventory().getItem(i) != null && t.getInventory().getItem(i) == null) {
                                        t.getInventory().setItem(i, backpackStored.getInventory().getItem(i));

                                        backpackStored.getInventory().clear(i);

                                        slots++;
                                    }
                                }

                                for (int i = 36; i <= 40; i++) {
                                    switch (i) {
                                        case 37 -> {
                                            if (backpackStored.getInventory().getItem(i) != null && t.getInventory().getHelmet() == null) {
                                                t.getInventory().setItem(103, backpackStored.getInventory().getItem(i));

                                                backpackStored.getInventory().clear(i);

                                                slots++;
                                            }
                                        }
                                        case 38 -> {
                                            if (backpackStored.getInventory().getItem(i) != null && t.getInventory().getChestplate() == null) {
                                                t.getInventory().setItem(102, backpackStored.getInventory().getItem(i));

                                                backpackStored.getInventory().clear(i);

                                                slots++;
                                            }
                                        }
                                        case 39 -> {
                                            if (backpackStored.getInventory().getItem(i) != null && t.getInventory().getLeggings() == null) {
                                                t.getInventory().setItem(101, backpackStored.getInventory().getItem(i));

                                                backpackStored.getInventory().clear(i);

                                                slots++;
                                            }
                                        }
                                        case 40 -> {
                                            if (backpackStored.getInventory().getItem(i) != null && t.getInventory().getBoots() == null) {
                                                t.getInventory().setItem(100, backpackStored.getInventory().getItem(i));

                                                backpackStored.getInventory().clear(i);

                                                slots++;
                                            }
                                        }
                                    }
                                }

                                if (backpackStored.getInventory().getItem(41) != null && t.getInventory().getItem(-106) == null) {
                                    backpackStored.getInventory().setItem(-106, backpackStored.getInventory().getItem(41));

                                    backpackStored.getInventory().clear(41);

                                    slots++;
                                }

                                if (slots == 0) {
                                    player.sendMessage(CCPlugin.getPrefix() + "Das gelagerte Backpack ist leer");
                                }
                                else {
                                    int notEmptySlots = 0;

                                    for (int i = 0; i <= 41; i++) {
                                        if (backpackStored.getInventory().getItem(i) != null) {
                                            notEmptySlots++;
                                        }
                                    }

                                    if (notEmptySlots != 0) {
                                        player.sendMessage(CCPlugin.getPrefix() + "Ein paar Items sind zurückgeblieben!");
                                        t.sendMessage(CCPlugin.getPrefix() + "Ein paar Items sind leider nicht zurück, weil du an den entsprechenden Slots Items hattest!");
                                    }

                                    player.sendMessage(CCPlugin.getPrefix() + "Das Inventar von " + t.getName() + " wurde erfolgreich zurück gegeben!");
                                    t.sendMessage(CCPlugin.getPrefix() + "Dein Inventar wurde zurück gegeben! Netter Admin haha");
                                }
                            }
                            else {
                                player.sendMessage(CCPlugin.getPrefix() + "Dieser Spieler ist gerade in FFA. Also bitte cheate im keine Items!");
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                            }
                        }
                    }
                    catch (NullPointerException e) {
                        player.sendMessage("Dieser Spieler existiert nicht!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(CCPlugin.getPrefix() + usage);
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return true;
    }
}
