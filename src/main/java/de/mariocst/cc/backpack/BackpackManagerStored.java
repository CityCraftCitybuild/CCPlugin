package de.mariocst.cc.backpack;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configs.BackpacksStored;

import java.io.IOException;
import java.util.*;

public class BackpackManagerStored {
    private final Map<UUID, BackpackStored> map;

    public BackpackManagerStored() {
        map = new HashMap<>();

        load();
    }

    public BackpackStored getBackpackStored(UUID uuid) {
        if (map.containsKey(uuid)) {
            return map.get(uuid);
        }

        BackpackStored backpack = new BackpackStored(uuid);
        map.put(uuid, backpack);
        return backpack;
    }

    public void setBackpack(UUID uuid, BackpackStored backpack) {
        map.put(uuid, backpack);
    }

    public void load() {
        BackpacksStored backpacksStored = CCPlugin.getInstance().getBackpacksStored();

        List<String> uuids = backpacksStored.getBackpacksStored().getStringList("backpacksstored");

        uuids.forEach(s -> {
            UUID uuid = UUID.fromString(s);

            String base64 = backpacksStored.getBackpacksStored().getString("backpackstored." + s);

            try {
                map.put(uuid, new BackpackStored(uuid, base64));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void save() {
        BackpacksStored backpacksStored = CCPlugin.getInstance().getBackpacksStored();

        List<String> uuids = new ArrayList<>();

        for (UUID uuid : map.keySet()) {
            uuids.add(uuid.toString());
        }

        backpacksStored.getBackpacksStored().set("backpacksstored", uuids);
        map.forEach((uuid, backpack) -> backpacksStored.getBackpacksStored().set("backpackstored." + uuid.toString(), backpack.toBase64()));
    }
}
