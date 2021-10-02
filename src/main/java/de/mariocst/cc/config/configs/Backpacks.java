package de.mariocst.cc.config.configs;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Backpacks {
    private final File file;
    private final YamlConfiguration backpacks;

    public Backpacks() {
        File dir = new File("./plugins/CityCraft");

        this.file = new File(dir, "backpacks.yml");

        this.backpacks = YamlConfiguration.loadConfiguration(file);
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getBackpacks() {
        return backpacks;
    }

    public void save() {
        try {
            backpacks.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
