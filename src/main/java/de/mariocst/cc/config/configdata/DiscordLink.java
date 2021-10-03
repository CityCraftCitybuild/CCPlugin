package de.mariocst.cc.config.configdata;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configs.Config;

public class DiscordLink {
    private String link;

    private static DiscordLink discordLink;

    public DiscordLink() {
        discordLink = this;

        Config config = CCPlugin.getInstance().getConfiguration();

        if (config.getConfig().contains("discord")) {
            this.link = config.getConfig().getString("discord");
        }
        else {
            this.link = "Not in use";
        }
    }

    public static DiscordLink getDiscordLink() {
        return discordLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void save() {
        Config config = CCPlugin.getInstance().getConfiguration();

        config.getConfig().set("discord", this.link);
    }
}
