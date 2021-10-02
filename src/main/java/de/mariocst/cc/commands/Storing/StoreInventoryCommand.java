package de.mariocst.cc.commands.Storing;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.backpack.BackpackStored;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StoreInventoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        final String usage = "/storeinventory <Spieler>";

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
                                    if (t.getInventory().getItem(i - 1) != null) {
                                        backpackStored.getInventory().setItem(i, t.getInventory().getItem(i - 1));

                                        t.getInventory().clear(i - 1);

                                        slots++;
                                    }
                                }

                                for (int i = 36; i <= 40; i++) {
                                    switch (i) {
                                        case 37 -> {
                                            if (t.getInventory().getHelmet() != null) {
                                                backpackStored.getInventory().setItem(i, t.getInventory().getHelmet());

                                                t.getInventory().clear(103);

                                                slots++;
                                            }
                                        }
                                        case 38 -> {
                                            if (t.getInventory().getChestplate() != null) {
                                                backpackStored.getInventory().setItem(i, t.getInventory().getChestplate());

                                                t.getInventory().clear(102);

                                                slots++;
                                            }
                                        }
                                        case 39 -> {
                                            if (t.getInventory().getLeggings() != null) {
                                                backpackStored.getInventory().setItem(i, t.getInventory().getLeggings());

                                                t.getInventory().clear(101);

                                                slots++;
                                            }
                                        }
                                        case 40 -> {
                                            if (t.getInventory().getBoots() != null) {
                                                backpackStored.getInventory().setItem(i, t.getInventory().getBoots());

                                                t.getInventory().clear(100);

                                                slots++;
                                            }
                                        }
                                    }
                                }

                                if (t.getInventory().getItem(-106) != null) {
                                    backpackStored.getInventory().setItem(41, t.getInventory().getItem(-106));

                                    t.getInventory().clear(-106);

                                    slots++;
                                }

                                if (slots == 0) {
                                    sender.sendMessage(CCPlugin.getPrefix() + "Das Inventar des Spielers ist leer haha");
                                }
                                else {
                                    sender.sendMessage(CCPlugin.getPrefix() + "Das Inventar von " + t.getName() + " wurde erfolgreich gelagert!");
                                    t.sendMessage(CCPlugin.getPrefix() + "Dein Inventar wurde gelagert! Frage einen Admin, um dein Inventar wieder zu bekommen lol");
                                }
                            }
                            else {
                                sender.sendMessage(CCPlugin.getPrefix() + "Bitte nimm dem Spieler die Items von FFA nicht weg. Danke");
                            }
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        sender.sendMessage("Dieser Spieler existiert nicht!");
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(CCPlugin.getPrefix() + usage);
            }
            return true;
        }

        if (player.hasPermission("mario.storeinventory") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length == 1) {
                    Player t = CCPlugin.getInstance().getServer().getPlayer(args[0]);

                    try {
                        if (t != null) {
                            if (!t.getWorld().getName().equalsIgnoreCase("ffa")) {
                                BackpackStored backpackStored = CCPlugin.getInstance().getBackpackManagerStored().getBackpackStored(t.getUniqueId());

                                int slots = 0;

                                for (int i = 0; i <= 36; i++) {
                                    if (t.getInventory().getItem(i) != null) {
                                        backpackStored.getInventory().setItem(i, t.getInventory().getItem(i));

                                        t.getInventory().clear(i);

                                        slots++;
                                    }
                                }

                                for (int i = 36; i <= 40; i++) {
                                    switch (i) {
                                        case 37 -> {
                                            if (t.getInventory().getHelmet() != null) {
                                                backpackStored.getInventory().setItem(i, t.getInventory().getHelmet());

                                                t.getInventory().clear(97);

                                                slots++;
                                            }
                                        }
                                        case 38 -> {
                                            if (t.getInventory().getChestplate() != null) {
                                                backpackStored.getInventory().setItem(i, t.getInventory().getChestplate());

                                                t.getInventory().clear(98);

                                                slots++;
                                            }
                                        }
                                        case 39 -> {
                                            if (t.getInventory().getLeggings() != null) {
                                                backpackStored.getInventory().setItem(i, t.getInventory().getLeggings());

                                                t.getInventory().clear(99);

                                                slots++;
                                            }
                                        }
                                        case 40 -> {
                                            if (t.getInventory().getBoots() != null) {
                                                backpackStored.getInventory().setItem(i, t.getInventory().getBoots());

                                                t.getInventory().clear(100);

                                                slots++;
                                            }
                                        }
                                    }
                                }

                                if (t.getInventory().getItem(101) != null) {
                                    backpackStored.getInventory().setItem(101, t.getInventory().getItem(101));

                                    t.getInventory().clear(101);

                                    slots++;
                                }

                                if (slots == 0) {
                                    player.sendMessage(CCPlugin.getPrefix() + "Das Inventar des Spielers ist leer haha");
                                }
                                else {
                                    player.sendMessage(CCPlugin.getPrefix() + "Das Inventar von " + t.getName() + " wurde erfolgreich gelagert!");
                                    t.sendMessage(CCPlugin.getPrefix() + "Dein Inventar wurde gelagert! Frage einen Admin, um dein Inventar wieder zu bekommen lol");
                                }
                            }
                            else {
                                player.sendMessage(CCPlugin.getPrefix() + "Bitte nimm dem Spieler die Items von FFA nicht weg. Danke");
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                            }
                        }
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                        player.sendMessage("Dieser Spieler existiert nicht!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(CCPlugin.getPrefix() + usage);
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
            }
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
        }
        return true;
    }
}
