package de.mariocst.cc.config.configdata;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configs.Config;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;

public class StaffChatPrefix {
    private String prefix;

    public StaffChatPrefix() {
        Config config = CCPlugin.getInstance().getConfiguration();

        if (config.getConfig().contains("staffchatprefix")) {
            this.prefix = config.getConfig().getString("staffchatprefix");
        }
        else {
            this.prefix = "§8[§6StaffChat§8] §7%player_name% §8» §6";
        }
    }

    public String getPlayerPrefix(Player player) {
        TextComponent component = (TextComponent) player.displayName();
        String disName = component.toString();

        return this.getPrefix().replaceAll("%player_name%", player.getName()).replaceAll("%player_disname%", disName);
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
