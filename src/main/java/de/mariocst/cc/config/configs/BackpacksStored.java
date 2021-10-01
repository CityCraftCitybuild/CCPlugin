package de.mariocst.cc.config.configs;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BackpacksStored {
    private final File file;
    private final YamlConfiguration backpacksStored;

    public BackpacksStored() {

        File dir = new File("./plugins/CityCraft");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        this.file = new File(dir, "backpacksstored.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.backpacksStored = YamlConfiguration.loadConfiguration(file);
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getBackpacksStored() {
        return backpacksStored;
    }

    public void save() {
        try {
            backpacksStored.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
