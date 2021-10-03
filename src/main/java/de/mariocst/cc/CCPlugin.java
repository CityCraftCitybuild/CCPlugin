package de.mariocst.cc;

import de.mariocst.cc.backpack.*;
import de.mariocst.cc.commands.Chat.*;
import de.mariocst.cc.commands.Inventory.*;
import de.mariocst.cc.commands.Invsee.*;
import de.mariocst.cc.commands.Others.*;
import de.mariocst.cc.commands.Player.*;
import de.mariocst.cc.commands.Send.*;
import de.mariocst.cc.commands.Server.*;
import de.mariocst.cc.commands.Setter.*;
import de.mariocst.cc.commands.Storing.*;
import de.mariocst.cc.commands.World.*;
import de.mariocst.cc.config.configdata.*;
import de.mariocst.cc.config.configs.*;
import de.mariocst.cc.listeners.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public final class CCPlugin extends JavaPlugin {
    private static String prefix;
    private static CCPlugin instance;

    private BackpackManager backpackManager;
    private BackpackManagerLarge backpackManagerLarge;
    private BackpackManagerStored backpackManagerStored;
    private InventoryDataManager inventoryDataManager;
    private Worlds worlds;
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

    public List<Player> invTroll = new ArrayList<>();
    public List<Player> staffChat = new ArrayList<>();
    public List<Player> godmode = new ArrayList<>();

    @Override
    public void onLoad() {
        instance = this;
        copyOldFiles();

        backpacks = new Backpacks();
        backpacksLarge = new BackpacksLarge();
        backpacksStored = new BackpacksStored();
        inventories = new Inventories();
        worlds = new Worlds();
        config = new Config();
    }

    @Override
    public void onEnable() {
        this.register();
        listenerRegistration();

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
        backpackManager = new BackpackManager();
        backpackManagerLarge = new BackpackManagerLarge();
        backpackManagerStored = new BackpackManagerStored();
        inventoryDataManager = new InventoryDataManager();

        log("CityCraft Plugin geladen!");
    }

    @Override
    public void onDisable() {
        log("CityCraft Plugin entladen!");
        saveConfigs();
    }

    public Config getConfiguration() {
        return config;
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
        pluginManager.registerEvents(new GodmodeListener(), this);
        pluginManager.registerEvents(new InventoryListener(), this);
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new KickListener(), this);
        pluginManager.registerEvents(new NavigatorListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new ServerListPingListener(), this);
        pluginManager.registerEvents(new WorldChangeListener(), this);
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
        backpackManager.save();
        backpackManagerLarge.save();
        backpackManagerStored.save();
        inventoryDataManager.save();
        backpacks.save();
        backpacksLarge.save();
        backpacksStored.save();
        inventories.save();
        worlds.save();
        config.save();
    }

    public void reloadConfigs() {
        backpacks = new Backpacks();
        backpacksLarge = new BackpacksLarge();
        backpacksStored = new BackpacksStored();
        inventories = new Inventories();
        worlds = new Worlds();
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
        backpackManager = new BackpackManager();
        backpackManagerLarge = new BackpackManagerLarge();
        backpackManagerStored = new BackpackManagerStored();
        inventoryDataManager = new InventoryDataManager();
    }

    public void log(String text) {
        this.getServer().getLogger().info(getPrefix() + text);
    }

    private void register() {
        //Chat
        Objects.requireNonNull(getCommand("broadcast")).setExecutor(new BroadcastCommand());
        Objects.requireNonNull(getCommand("chatclear")).setExecutor(new ChatClearCommand());
        Objects.requireNonNull(getCommand("colorcodes")).setExecutor(new ColorCodesCommand());

        //Inventory
        Objects.requireNonNull(getCommand("backpack")).setExecutor(new BackpackCommand());
        Objects.requireNonNull(getCommand("backpacklarge")).setExecutor(new BackpackLargeCommand());
        Objects.requireNonNull(getCommand("clearenderchest")).setExecutor(new ClearEnderChestCommand());
        Objects.requireNonNull(getCommand("clearinventory")).setExecutor(new ClearInventoryCommand());
        Objects.requireNonNull(getCommand("unbreakable")).setExecutor(new UnbreakableCommand());

        //Invsee
        Objects.requireNonNull(getCommand("enderinvsee")).setExecutor(new EnderInvseeCommand());
        Objects.requireNonNull(getCommand("invsee")).setExecutor(new InvseeCommand());

        //Others
        Objects.requireNonNull(getCommand("discord")).setExecutor(new DiscordCommand());
        Objects.requireNonNull(getCommand("enderchest")).setExecutor(new ECCommand());
        Objects.requireNonNull(getCommand("troll")).setExecutor(new TrollCommand());
        Objects.requireNonNull(getCommand("web")).setExecutor(new WebCommand());

        //Player
        Objects.requireNonNull(getCommand("die")).setExecutor(new DieCommand());
        Objects.requireNonNull(getCommand("feed")).setExecutor(new FeedCommand());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCommand());
        Objects.requireNonNull(getCommand("gm")).setExecutor(new GMCommand());
        Objects.requireNonNull(getCommand("godmode")).setExecutor(new GodmodeCommand());
        Objects.requireNonNull(getCommand("heal")).setExecutor(new HealCommand());
        Objects.requireNonNull(getCommand("speed")).setExecutor(new SpeedCommand());
        Objects.requireNonNull(getCommand("sudo")).setExecutor(new SudoCommand());
        Objects.requireNonNull(getCommand("weed")).setExecutor(new WeedCommand());

        //Send
        Objects.requireNonNull(getCommand("sendactionbar")).setExecutor(new SendActionBarCommand());
        Objects.requireNonNull(getCommand("sendmessage")).setExecutor(new SendMessageCommand());
        Objects.requireNonNull(getCommand("sendtitle")).setExecutor(new SendTitleCommand());

        //Server
        Objects.requireNonNull(getCommand("banall")).setExecutor(new BanAllCommand());
        Objects.requireNonNull(getCommand("config")).setExecutor(new ConfigCommand());
        Objects.requireNonNull(getCommand("kickall")).setExecutor(new KickAllCommand());
        Objects.requireNonNull(getCommand("onlineplayers")).setExecutor(new OnlinePlayersCommand());
        Objects.requireNonNull(getCommand("report")).setExecutor(new ReportCommand());
        Objects.requireNonNull(getCommand("staffchat")).setExecutor(new StaffChatCommand());

        //Setter
        Objects.requireNonNull(getCommand("setlink")).setExecutor(new SetLinkCommand());
        Objects.requireNonNull(getCommand("setprefix")).setExecutor(new SetPrefixCommand());

        //Storing
        Objects.requireNonNull(getCommand("backpackstored")).setExecutor(new BackpackStoredCommand());
        Objects.requireNonNull(getCommand("ffaarmor")).setExecutor(new FFAArmorCommand());
        Objects.requireNonNull(getCommand("ffainventory")).setExecutor(new FFAInventoryCommand());
        Objects.requireNonNull(getCommand("restoreinventory")).setExecutor(new RestoreInventoryCommand());
        Objects.requireNonNull(getCommand("storeinventory")).setExecutor(new StoreInventoryCommand());

        //World
        Objects.requireNonNull(getCommand("cb")).setExecutor(new CBCommand());
        Objects.requireNonNull(getCommand("day")).setExecutor(new DayCommand());
        Objects.requireNonNull(getCommand("end")).setExecutor(new EndCommand());
        Objects.requireNonNull(getCommand("farmwelt")).setExecutor(new FarmweltCommand());
        Objects.requireNonNull(getCommand("ffa")).setExecutor(new FFACommand());
        Objects.requireNonNull(getCommand("forceloadchunk")).setExecutor(new ForceLoadChunkCommand());
        Objects.requireNonNull(getCommand("getentities")).setExecutor(new GetEntitesCommand());
        Objects.requireNonNull(getCommand("killradius")).setExecutor(new KillRadiusCommand());
        Objects.requireNonNull(getCommand("lobby")).setExecutor(new LobbyCommand());
        Objects.requireNonNull(getCommand("navigator")).setExecutor(new NavigatorCommand());
        Objects.requireNonNull(getCommand("nether")).setExecutor(new NetherCommand());
        Objects.requireNonNull(getCommand("night")).setExecutor(new NightCommand());
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

    private void copyOldFiles() {
        File dir = new File("./plugins/CookieCraft");

        if (dir.listFiles() == null) return;

        File newDir = new File("./plugins/CityCraft");

        for (File ignored1 : Objects.requireNonNull(dir.listFiles())) {
            try {
                Files.copy(dir.toPath(), newDir.toPath());
            }
            catch (IOException ignored) { }
        }
    }
}
