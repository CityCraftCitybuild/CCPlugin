package de.mariocst.cc.config.configdata;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configs.Worlds;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EndData {
    private static EndData endData;

    private final Worlds config = CCPlugin.getInstance().getWorldsConfig();

    private String worldName;
    private double x, y, z;
    private float yaw, pitch;

    public EndData() {
        endData = this;

        if (config.getConfig().contains("end") && this.config.getConfig().getConfigurationSection("end") != null) {
            Map<String, Object> values = Objects.requireNonNull(this.config.getConfig().getConfigurationSection("end")).getValues(true);

            if (!values.containsKey("world_name")) {
                values.put("world_name", "world_the_end");
            }

            if (!values.containsKey("x")) {
                values.put("x", "0");
            }

            if (!values.containsKey("y")) {
                values.put("y", "100");
            }

            if (!values.containsKey("z")) {
                values.put("z", "0");
            }

            if (!values.containsKey("yaw")) {
                values.put("yaw", "90");
            }

            if (!values.containsKey("pitch")) {
                values.put("pitch", "0");
            }

            int round = 0;

            for (Map.Entry<String, Object> entry : values.entrySet()) {
                round++;

                if (round == 7) return;

                switch (entry.getKey().toLowerCase()) {
                    case "world_name" -> this.worldName = entry.getValue().toString();
                    case "x" -> this.x = Double.parseDouble(entry.getValue().toString());
                    case "y" -> this.y = Double.parseDouble(entry.getValue().toString());
                    case "z" -> this.z = Double.parseDouble(entry.getValue().toString());
                    case "yaw" -> this.yaw = Float.parseFloat(entry.getValue().toString());
                    case "pitch" -> this.pitch = Float.parseFloat(entry.getValue().toString());
                }
            }
        }
        else {
            this.worldName = "world_the_end";
            this.x = 0.0D;
            this.y = 100.0D;
            this.z = 0.0D;
            this.yaw = 90.0F;
            this.pitch = 0.0F;
        }
    }

    public static EndData getEndData() {
        return endData;
    }

    public Worlds getConfig() {
        return config;
    }

    public String getWorldName() {
        return worldName;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void save() {
        if (config.getConfig().contains("end") && this.config.getConfig().getConfigurationSection("end") != null) {
            Map<String, Object> values = Objects.requireNonNull(this.config.getConfig().getConfigurationSection("end")).getValues(true);

            values.put("world_name", this.worldName);
            values.put("x", this.x);
            values.put("y", this.y);
            values.put("z", this.z);
            values.put("yaw", this.yaw);
            values.put("pitch", this.pitch);

            config.getConfig().set("end", values);
        }
        else {
            Map<String, Object> values = new HashMap<>();

            values.put("world_name", this.worldName);
            values.put("x", this.x);
            values.put("y", this.y);
            values.put("z", this.z);
            values.put("yaw", this.yaw);
            values.put("pitch", this.pitch);

            config.getConfig().set("end", values);
        }
    }
}
