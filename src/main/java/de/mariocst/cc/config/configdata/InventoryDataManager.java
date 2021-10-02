package de.mariocst.cc.config.configdata;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configs.Inventories;

import java.io.IOException;
import java.util.*;

public class InventoryDataManager {
    private final Map<UUID, InventoryData> map;

    public InventoryDataManager() {
        map = new HashMap<>();

        load();
    }

    public InventoryData getInventory(UUID uuid) {
        if (map.containsKey(uuid)) {
            return map.get(uuid);
        }

        InventoryData inventory = new InventoryData(uuid);
        map.put(uuid, inventory);
        return inventory;
    }

    public void load() {
        Inventories inventories = CCPlugin.getInstance().getInventories();

        List<String> uuids = inventories.getInventories().getStringList("inventories");

        uuids.forEach(s -> {
            UUID uuid = UUID.fromString(s);

            String base64 = inventories.getInventories().getString("inventory." + s);

            try {
                map.put(uuid, new InventoryData(uuid, base64));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void save() {
        Inventories inventories = CCPlugin.getInstance().getInventories();

        List<String> uuids = new ArrayList<>();

        for (UUID uuid : map.keySet()) {
            uuids.add(uuid.toString());
        }

        inventories.getInventories().set("inventories", uuids);
        map.forEach((uuid, backpack) -> inventories.getInventories().set("inventory." + uuid.toString(), backpack.toBase64()));
    }
}
