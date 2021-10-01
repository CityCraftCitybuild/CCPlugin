package de.mariocst.cc.config.configs;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BackpacksLarge {
    private final File file;
    private final YamlConfiguration backpacksLarge;

    public BackpacksLarge() {

        File dir = new File("./plugins/CityCraft");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        this.file = new File(dir, "backpackslarge.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.backpacksLarge = YamlConfiguration.loadConfiguration(file);
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getBackpacksLarge() {
        return backpacksLarge;
    }

    public void save() {
        try {
            backpacksLarge.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
