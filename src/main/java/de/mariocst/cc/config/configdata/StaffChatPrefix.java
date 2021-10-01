package de.mariocst.cc.config.configdata;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configs.Config;
import org.bukkit.entity.Player;

public class StaffChatPrefix {
    private String prefix;

    private static StaffChatPrefix staffChatPrefix;

    public StaffChatPrefix() {
        staffChatPrefix = this;

        Config config = CCPlugin.getInstance().getConfiguration();

        if (config.getConfig().contains("staffchatprefix")) {
            this.prefix = config.getConfig().getString("staffchatprefix");
        }
        else {
            this.prefix = "§8[§6StaffChat§8] §7%player_name% §8» §6";
        }
    }

    public void reload() {
        Config config = CCPlugin.getInstance().getConfiguration();

        if (config.getConfig().contains("staffchatprefix")) {
            this.prefix = config.getConfig().getString("staffchatprefix");
        }
        else {
            this.prefix = "§8[§6StaffChat§8] §7%player_name% §8» §6";
        }
    }

    public static StaffChatPrefix getStaffChatPrefix() {
        return staffChatPrefix;
    }

    public String getPlayerPrefix(Player player) {
        return this.getPrefix().replaceAll("%player_name%", player.getName()).replaceAll("%player_disname%", player.getDisplayName());
    }

    public String getConsolePrefix() {
        return this.getPrefix().replaceAll("%player_name%", "Konsole").replaceAll("%player_disname%", "Konsole");
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void save() {
        Config config = CCPlugin.getInstance().getConfiguration();

        config.getConfig().set("staffchatprefix", this.prefix);
    }
}
