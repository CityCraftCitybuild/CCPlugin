package de.mariocst.cc.commands.World;

import de.mariocst.cc.CCPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.geysermc.floodgate.api.FloodgateApi;
import org.jetbrains.annotations.NotNull;

public class NavigatorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            CCPlugin.getInstance().log("Dieser Command kann leider nur InGame ausgeführt werden!");
            return true;
        }

        if (player.hasPermission("mario.nav") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
                CCPlugin.getInstance().getNavigatorForm().openNavigator(player);
                return true;
            }

            player.closeInventory();

            Inventory inventory = CCPlugin.getInstance().getServer().createInventory(player, 54, Component.text("§cNavigator"));

            ItemStack farmwelt = new ItemStack(Material.GRASS_BLOCK, 1);
            ItemMeta farmweltMeta = farmwelt.getItemMeta();

            ItemStack nether = new ItemStack(Material.NETHERRACK, 1);
            ItemMeta netherMeta = nether.getItemMeta();

            ItemStack end = new ItemStack(Material.END_STONE, 1);
            ItemMeta endMeta = end.getItemMeta();

            ItemStack cb = new ItemStack(Material.SMOOTH_QUARTZ, 1);
            ItemMeta cbMeta = cb.getItemMeta();

            ItemStack lobby = new ItemStack(Material.BEACON, 1);
            ItemMeta lobbyMeta = lobby.getItemMeta();

            ItemStack ffa = new ItemStack(Material.IRON_SWORD, 1);
            ItemMeta ffaMeta = ffa.getItemMeta();

            ItemStack close = new ItemStack(Material.BARRIER, 1);
            ItemMeta closeMeta = close.getItemMeta();

            ItemStack redGlassPane = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
            ItemMeta redGlassPaneMeta = redGlassPane.getItemMeta();

            farmweltMeta.displayName(Component.text("§2Farmwelt"));

            farmwelt.setItemMeta(farmweltMeta);

            netherMeta.displayName(Component.text("§cNether"));

            nether.setItemMeta(netherMeta);

            endMeta.displayName(Component.text("§eEnd"));

            end.setItemMeta(endMeta);

            cbMeta.displayName(Component.text("§3CityBuild"));

            cb.setItemMeta(cbMeta);

            lobbyMeta.displayName(Component.text("§bLobby"));

            lobby.setItemMeta(lobbyMeta);

            ffaMeta.displayName(Component.text("§4FFA"));

            ffa.setItemMeta(ffaMeta);

            closeMeta.displayName(Component.text("§cSchließen"));

            close.setItemMeta(closeMeta);

            redGlassPaneMeta.displayName(Component.text(""));

            redGlassPane.setItemMeta(redGlassPaneMeta);

            inventory.setItem(11, farmwelt);
            inventory.setItem(20, nether);
            inventory.setItem(29, end);
            inventory.setItem(15, cb);
            inventory.setItem(22, lobby);
            inventory.setItem(24, ffa);
            inventory.setItem(53, close);

            for (int i = 0; i <= 53; i++) {
                if (inventory.getItem(i) == null) inventory.setItem(i, redGlassPane);
            }

            player.openInventory(inventory);
        }
        else {
            player.sendMessage(CCPlugin.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }
}
