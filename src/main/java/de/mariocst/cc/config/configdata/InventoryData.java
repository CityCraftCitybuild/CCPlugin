package de.mariocst.cc.config.configdata;

import de.mariocst.cc.backpack.Base64;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.io.IOException;
import java.util.UUID;

public class InventoryData {
    private final UUID uuid;
    private final Inventory inventory;

    public InventoryData(UUID uuid) {
        this.uuid = uuid;
        this.inventory = Bukkit.createInventory(null, 54, Component.text("Inventory"));
    }

    public InventoryData(UUID uuid, String base64) throws IOException {
        this.uuid = uuid;
        this.inventory = Bukkit.createInventory(null, 54, Component.text("Inventory"));
        this.inventory.setContents(Base64.itemStackArrayFromBase64(base64));
    }

    public UUID getUuid() {
        return uuid;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String toBase64() {
        return Base64.itemStackArrayToBase64(inventory.getContents());
    }
}
