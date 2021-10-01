package de.mariocst.cc.config.configdata;

import de.mariocst.cc.CCPlugin;
import de.mariocst.cc.config.configs.Config;

public class WebLink {
    private String link;

    private static WebLink webLink;

    public WebLink() {
        webLink = this;

        Config config = CCPlugin.getInstance().getConfiguration();

        if (config.getConfig().contains("web")) {
            this.link = config.getConfig().getString("web");
        }
        else {
            this.link = "Not in use";
        }
    }

    public void reload() {
        Config config = CCPlugin.getInstance().getConfiguration();

        if (config.getConfig().contains("web")) {
            this.link = config.getConfig().getString("web");
        }
        else {
            this.link = "Not in use";
        }
    }

    public static WebLink getWebLink() {
        return webLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void save() {
        Config config = CCPlugin.getInstance().getConfiguration();

        config.getConfig().set("web", this.link);
    }
}
