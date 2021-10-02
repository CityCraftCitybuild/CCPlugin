package de.mariocst.cc.config.configs;

import de.mariocst.cc.CCPlugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Worlds {
    private final File file;
    private final YamlConfiguration config;

    public Worlds() {
        File dir = new File("./plugins/CityCraft");

        this.file = new File(dir, "welten.yml");

        if (!file.exists()) {
            try (InputStream in = CCPlugin.getInstance().getResource("welten.yml")) {
                assert in != null;
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
