package de.mariocst.cc;

import de.mariocst.cc.backpack.*;
import de.mariocst.cc.commands.Admin.*;
import de.mariocst.cc.commands.Chat.*;
import de.mariocst.cc.commands.Inventory.*;
import de.mariocst.cc.commands.Invsee.*;
import de.mariocst.cc.commands.Others.*;
import de.mariocst.cc.commands.Player.*;
import de.mariocst.cc.commands.Plot.*;
import de.mariocst.cc.commands.Send.*;
import de.mariocst.cc.commands.Server.*;
import de.mariocst.cc.commands.Setter.*;
import de.mariocst.cc.commands.Storing.*;
import de.mariocst.cc.commands.World.*;
import de.mariocst.cc.config.configdata.*;
import de.mariocst.cc.config.configs.*;
import de.mariocst.cc.forms.*;
import de.mariocst.cc.listeners.*;
import de.mariocst.cc.webhook.*;

import lombok.Getter;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class CCPlugin extends JavaPlugin {
    private static String prefix;
    private static CCPlugin instance;

    private BackpackManager backpackManager;
    private BackpackManagerLarge backpackManagerLarge;
    private BackpackManagerStored backpackManagerStored;
    private InventoryDataManager inventoryDataManager;
    private Worlds worlds;
    private DiscordConfig discordConfig;
    private Config config;
    private Backpacks backpacks;
    private BackpacksLarge backpacksLarge;
    private BackpacksStored backpacksStored;
    private Inventories inventories;
    private Prefix prefixConfig;
    private StaffChatPrefix staffChatPrefix;
    private DiscordLink discordLink;
    private WebLink webLink;
    private FlyWorlds flyWorlds;
    private CB01Data cb01Data;
    private EndData endData;
    private FarmweltData farmweltData;
    private FFAData ffaData;
    private LobbyData lobbyData;
    private NetherData netherData;
    private DiscordConfigData discordConfigData;

    public List<Player> invTroll = new ArrayList<>();
    public List<Player> staffChat = new ArrayList<>();
    public List<Player> godMode = new ArrayList<>();

    @Getter
    public NavigatorForm navigatorForm;

    @Getter
    public RandForm randForm;

    @Getter
    public ReportForm reportForm;

    @Override
    public void onLoad() {
        instance = this;

        this.loadConfigs();
    }

    @Override
    public void onEnable() {
        try {
            this.register();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            this.warn("Error beim Initialisieren der Commands! Deaktiviere Plugin...");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.listenerRegistration();

        if (discordConfigData.getUrl().equals("")) this.log("Es ist kein Webhook Link angegeben!");

        this.log("CityCraft Plugin geladen!");
    }

    @Override
    public void onDisable() {
        this.log("CityCraft Plugin entladen!");
        this.saveConfigs();
    }

    public void saveConfigs() {
        prefixConfig.save();
        staffChatPrefix.save();
        discordLink.save();
        webLink.save();
        flyWorlds.save();
        cb01Data.save();
        endData.save();
        farmweltData.save();
        ffaData.save();
        lobbyData.save();
        netherData.save();
        discordConfigData.save();
        backpackManager.save();
        backpackManagerLarge.save();
        backpackManagerStored.save();
        inventoryDataManager.save();
        backpacks.save();
        backpacksLarge.save();
        backpacksStored.save();
        inventories.save();
        worlds.save();
        discordConfig.save();
        config.save();
    }

    public void loadConfigs() {
        backpacks = new Backpacks();
        backpacksLarge = new BackpacksLarge();
        backpacksStored = new BackpacksStored();
        inventories = new Inventories();
        worlds = new Worlds();
        discordConfig = new DiscordConfig();
        config = new Config();
        prefixConfig = new Prefix();
        staffChatPrefix = new StaffChatPrefix();
        discordLink = new DiscordLink();
        webLink = new WebLink();
        flyWorlds = new FlyWorlds();
        cb01Data = new CB01Data();
        endData = new EndData();
        farmweltData = new FarmweltData();
        ffaData = new FFAData();
        lobbyData = new LobbyData();
        netherData = new NetherData();
        discordConfigData = new DiscordConfigData();
        backpackManager = new BackpackManager();
        backpackManagerLarge = new BackpackManagerLarge();
        backpackManagerStored = new BackpackManagerStored();
        inventoryDataManager = new InventoryDataManager();
    }

    public Config getConfiguration() {
        return config;
    }

    public DiscordConfig getDiscordConfig() {
        return discordConfig;
    }

    public Worlds getWorldsConfig() {
        return worlds;
    }

    public Backpacks getBackpacks() {
        return backpacks;
    }

    public BackpacksLarge getBackpacksLarge() {
        return backpacksLarge;
    }

    public BackpacksStored getBackpacksStored() {
        return backpacksStored;
    }

    public Inventories getInventories() {
        return inventories;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        CCPlugin.prefix = prefix;
    }

    private void listenerRegistration() {
        PluginManager pluginManager = this.getServer().getPluginManager();

        pluginManager.registerEvents(new AchievementListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new ChatListener(), this);
        pluginManager.registerEvents(new DeathListener(), this);
        pluginManager.registerEvents(new GodModeListener(), this);
        pluginManager.registerEvents(new InventoryListener(), this);
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new KickListener(), this);
        pluginManager.registerEvents(new NavigatorListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new RandListener(), this);
        pluginManager.registerEvents(new ScaffoldListener(), this);
        pluginManager.registerEvents(new ServerListPingListener(), this);
        pluginManager.registerEvents(new WorldChangeListener(), this);
    }

    public void log(String text) {
        this.getServer().getLogger().info(getPrefix() + text);
    }

    public void warn(String text) {
        this.getServer().getLogger().warning(getPrefix() + text);
    }

    private void register() throws NullPointerException {
        // Admin
        Objects.requireNonNull(this.getCommand("scaffold")).setExecutor(new ScaffoldCommand());

        // Chat
        Objects.requireNonNull(this.getCommand("broadcast")).setExecutor(new BroadcastCommand());
        Objects.requireNonNull(this.getCommand("chatclear")).setExecutor(new ChatClearCommand());
        Objects.requireNonNull(this.getCommand("colorcodes")).setExecutor(new ColorCodesCommand());

        // Inventory
        Objects.requireNonNull(this.getCommand("backpack")).setExecutor(new BackpackCommand());
        Objects.requireNonNull(this.getCommand("backpacklarge")).setExecutor(new BackpackLargeCommand());
        Objects.requireNonNull(this.getCommand("clearenderchest")).setExecutor(new ClearEnderChestCommand());
        Objects.requireNonNull(this.getCommand("clearinventory")).setExecutor(new ClearInventoryCommand());
        Objects.requireNonNull(this.getCommand("unbreakable")).setExecutor(new UnbreakableCommand());

        // Invsee
        Objects.requireNonNull(this.getCommand("enderinvsee")).setExecutor(new EnderInvseeCommand());
        Objects.requireNonNull(this.getCommand("invsee")).setExecutor(new InvseeCommand());

        // Others
        Objects.requireNonNull(this.getCommand("discord")).setExecutor(new DiscordCommand());
        Objects.requireNonNull(this.getCommand("enderchest")).setExecutor(new ECCommand());
        Objects.requireNonNull(this.getCommand("troll")).setExecutor(new TrollCommand());
        Objects.requireNonNull(this.getCommand("troll")).setTabCompleter(new TrollCommand());
        Objects.requireNonNull(this.getCommand("web")).setExecutor(new WebCommand());

        // Player
        Objects.requireNonNull(this.getCommand("die")).setExecutor(new DieCommand());
        Objects.requireNonNull(this.getCommand("feed")).setExecutor(new FeedCommand());
        Objects.requireNonNull(this.getCommand("fly")).setExecutor(new FlyCommand());
        Objects.requireNonNull(this.getCommand("gm")).setExecutor(new GMCommand());
        Objects.requireNonNull(this.getCommand("gm")).setTabCompleter(new GMCommand());
        Objects.requireNonNull(this.getCommand("godmode")).setExecutor(new GodModeCommand());
        Objects.requireNonNull(this.getCommand("heal")).setExecutor(new HealCommand());
        Objects.requireNonNull(this.getCommand("speed")).setExecutor(new SpeedCommand());
        Objects.requireNonNull(this.getCommand("speed")).setTabCompleter(new SpeedCommand());
        Objects.requireNonNull(this.getCommand("sudo")).setExecutor(new SudoCommand());
        Objects.requireNonNull(this.getCommand("weed")).setExecutor(new WeedCommand());

        // Plot
        Objects.requireNonNull(this.getCommand("rand")).setExecutor(new RandCommand());

        // Send
        Objects.requireNonNull(this.getCommand("sendactionbar")).setExecutor(new SendActionBarCommand());
        Objects.requireNonNull(this.getCommand("sendmessage")).setExecutor(new SendMessageCommand());
        Objects.requireNonNull(this.getCommand("sendtitle")).setExecutor(new SendTitleCommand());

        // Server
        Objects.requireNonNull(this.getCommand("banall")).setExecutor(new BanAllCommand());
        Objects.requireNonNull(this.getCommand("config")).setExecutor(new ConfigCommand());
        Objects.requireNonNull(this.getCommand("config")).setTabCompleter(new ConfigCommand());
        Objects.requireNonNull(this.getCommand("kickall")).setExecutor(new KickAllCommand());
        Objects.requireNonNull(this.getCommand("onlineplayers")).setExecutor(new OnlinePlayersCommand());
        Objects.requireNonNull(this.getCommand("report")).setExecutor(new ReportCommand());
        Objects.requireNonNull(this.getCommand("report")).setTabCompleter(new ReportCommand());
        Objects.requireNonNull(this.getCommand("staffchat")).setExecutor(new StaffChatCommand());
        Objects.requireNonNull(this.getCommand("staffchat")).setTabCompleter(new StaffChatCommand());

        // Setter
        Objects.requireNonNull(this.getCommand("setlink")).setExecutor(new SetLinkCommand());
        Objects.requireNonNull(this.getCommand("setlink")).setTabCompleter(new SetLinkCommand());
        Objects.requireNonNull(this.getCommand("setprefix")).setExecutor(new SetPrefixCommand());

        // Storing
        Objects.requireNonNull(this.getCommand("backpackstored")).setExecutor(new BackpackStoredCommand());
        Objects.requireNonNull(this.getCommand("ffainventory")).setExecutor(new FFAInventoryCommand());
        Objects.requireNonNull(this.getCommand("restoreinventory")).setExecutor(new RestoreInventoryCommand());
        Objects.requireNonNull(this.getCommand("storeinventory")).setExecutor(new StoreInventoryCommand());

        // World
        Objects.requireNonNull(this.getCommand("cb")).setExecutor(new CBCommand());
        Objects.requireNonNull(this.getCommand("day")).setExecutor(new DayCommand());
        Objects.requireNonNull(this.getCommand("end")).setExecutor(new EndCommand());
        Objects.requireNonNull(this.getCommand("farmwelt")).setExecutor(new FarmweltCommand());
        Objects.requireNonNull(this.getCommand("ffa")).setExecutor(new FFACommand());
        Objects.requireNonNull(this.getCommand("forceloadchunk")).setExecutor(new ForceLoadChunkCommand());
        Objects.requireNonNull(this.getCommand("getentities")).setExecutor(new GetEntitesCommand());
        Objects.requireNonNull(this.getCommand("killradius")).setExecutor(new KillRadiusCommand());
        Objects.requireNonNull(this.getCommand("lobby")).setExecutor(new LobbyCommand());
        Objects.requireNonNull(this.getCommand("navigator")).setExecutor(new NavigatorCommand());
        Objects.requireNonNull(this.getCommand("nether")).setExecutor(new NetherCommand());
        Objects.requireNonNull(this.getCommand("night")).setExecutor(new NightCommand());

        // Forms
        this.navigatorForm = new NavigatorForm();
        this.randForm = new RandForm();
        this.reportForm = new ReportForm();
    }

    public static CCPlugin getInstance() {
        return instance;
    }

    public BackpackManager getBackpackManager() {
        return backpackManager;
    }

    public BackpackManagerLarge getBackpackManagerLarge() {
        return backpackManagerLarge;
    }

    public BackpackManagerStored getBackpackManagerStored() {
        return backpackManagerStored;
    }

    public InventoryDataManager getInventoryDataManager() {
        return inventoryDataManager;
    }

    public StaffChatPrefix getStaffChatPrefix() {
        return staffChatPrefix;
    }

    public void sendReport(Player player, Player reported, String reason) throws IOException {
        DiscordWebhook webhook = new DiscordWebhook(discordConfigData.getUrl());

        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle(discordConfigData.getTitle())
                .setDescription(discordConfigData.getDescription())
                .addField("Spieler", player.getName(), false)
                .addField("Reported", reported.getName(), false)
                .addField("Grund", reason, false)
                .setColor(Color.RED));

        try {
            webhook.execute();
        }
        catch (IOException e) {
            this.getServer().getLogger().severe(e.getLocalizedMessage());
        }
    }

    public void sendReport(Player player, OfflinePlayer reported, String reason) throws IOException {
        DiscordWebhook webhook = new DiscordWebhook(discordConfigData.getUrl());

        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle(discordConfigData.getTitle())
                .setDescription(discordConfigData.getDescription())
                .addField("Spieler", player.getName(), false)
                .addField("Reported", reported.getName(), false)
                .addField("Grund", reason, false)
                .setColor(Color.RED));

        try {
            webhook.execute();
        }
        catch (IOException e) {
            this.getServer().getLogger().severe(e.getLocalizedMessage());
        }
    }
}
