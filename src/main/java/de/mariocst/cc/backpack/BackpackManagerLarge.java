package de.mariocst.cc.backpack;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configs.BackpacksLarge;

import java.io.IOException;
import java.util.*;

public class BackpackManagerLarge {
    private final Map<UUID, BackpackLarge> map;

    public BackpackManagerLarge() {
        map = new HashMap<>();

        load();
    }

    public BackpackLarge getBackpackLarge(UUID uuid) {
        if (map.containsKey(uuid)) {
            return map.get(uuid);
        }

        BackpackLarge backpack = new BackpackLarge(uuid);
        map.put(uuid, backpack);
        return backpack;
    }

    public void load() {
        BackpacksLarge backpacksLarge = CCPlugin.getInstance().getBackpacksLarge();

        List<String> uuids = backpacksLarge.getBackpacksLarge().getStringList("backpackslarge");

        uuids.forEach(s -> {
            UUID uuid = UUID.fromString(s);

            String base64 = backpacksLarge.getBackpacksLarge().getString("backpacklarge." + s);

            try {
                map.put(uuid, new BackpackLarge(uuid, base64));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void save() {
        BackpacksLarge backpacksLarge = CCPlugin.getInstance().getBackpacksLarge();

        List<String> uuids = new ArrayList<>();

        for (UUID uuid : map.keySet()) {
            uuids.add(uuid.toString());
        }

        backpacksLarge.getBackpacksLarge().set("backpackslarge", uuids);
        map.forEach((uuid, backpack) -> backpacksLarge.getBackpacksLarge().set("backpacklarge." + uuid.toString(), backpack.toBase64()));
    }
}
