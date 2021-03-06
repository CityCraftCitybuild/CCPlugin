package de.mariocst.cc.config.configs;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Inventories {
    private final File file;
    private final YamlConfiguration inventories;

    public Inventories() {
        File dir = new File("./plugins/CityCraft");

        this.file = new File(dir, "inventories.yml");

        this.inventories = YamlConfiguration.loadConfiguration(file);
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getInventories() {
        return inventories;
    }

    public void save() {
        try {
            inventories.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
