package de.mariocst.cc.config.configdata;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configs.Config;

import java.util.ArrayList;
import java.util.List;

public class FlyWorlds {
    private static FlyWorlds flyWorlds;

    private final List<String> worlds = new ArrayList<>();

    public FlyWorlds() {
        flyWorlds = this;

        Config config = CCPlugin.getInstance().getConfiguration();

        if (config.getConfig().contains("flyWorlds") && config.getConfig().getConfigurationSection("flyWorlds") != null) {
            worlds.addAll(config.getConfig().getStringList("flyWorlds"));
        }
        else {
            worlds.add("world");
        }
    }

    public void reload() {
        Config config = CCPlugin.getInstance().getConfiguration();

        if (config.getConfig().contains("flyWorlds") && config.getConfig().getConfigurationSection("flyWorlds") != null) {
            worlds.addAll(config.getConfig().getStringList("flyWorlds"));
        }
        else {
            worlds.add("world");
        }
    }

    public static FlyWorlds getFlyWorlds() {
        return flyWorlds;
    }

    public List<String> getWorlds() {
        return worlds;
    }

    public void save() {
        Config config = CCPlugin.getInstance().getConfiguration();

        config.getConfig().set("flyWorlds", worlds);
    }
}
