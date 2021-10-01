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
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
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
    private CB02Data cb02Data;
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
        cb02Data = new CB02Data();
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
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new AchievementListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new ChatListener(), this);
        pluginManager.registerEvents(new DeathListener(), this);
        pluginManager.registerEvents(new GodmodeListener(), this);
        pluginManager.registerEvents(new InventoryListener(), this);
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new NavigatorListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new WorldChangeListener(), this);
    }

    public void saveConfigs() {
        prefixConfig.save();
        staffChatPrefix.save();
        discordLink.save();
        webLink.save();
        flyWorlds.save();
        cb01Data.save();
        cb02Data.save();
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
        cb02Data = new CB02Data();
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

    public void warning(String text) {
        this.getServer().getLogger().warning(getPrefix() + text);
    }

    private void register() {
        //Chat
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("chatclear").setExecutor(new ChatClearCommand());
        getCommand("colorcodes").setExecutor(new ColorCodesCommand());

        //Inventory
        getCommand("backpack").setExecutor(new BackpackCommand());
        getCommand("backpacklarge").setExecutor(new BackpackLargeCommand());
        getCommand("clearenderchest").setExecutor(new ClearEnderChestCommand());
        getCommand("clearinventory").setExecutor(new ClearInventoryCommand());
        getCommand("unbreakable").setExecutor(new UnbreakableCommand());

        //Invsee
        getCommand("enderinvsee").setExecutor(new EnderInvseeCommand());
        getCommand("invsee").setExecutor(new InvseeCommand());

        //Others
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("enderchest").setExecutor(new ECCommand());
        getCommand("troll").setExecutor(new TrollCommand());
        getCommand("web").setExecutor(new WebCommand());

        //Player
        getCommand("die").setExecutor(new DieCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("gm").setExecutor(new GMCommand());
        getCommand("godmode").setExecutor(new GodmodeCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("speed").setExecutor(new SpeedCommand());
        getCommand("sudo").setExecutor(new SudoCommand());
        getCommand("weed").setExecutor(new WeedCommand());

        //Send
        getCommand("sendactionbar").setExecutor(new SendActionBarCommand());
        getCommand("sendmessage").setExecutor(new SendMessageCommand());
        getCommand("sendtitle").setExecutor(new SendTitleCommand());

        //Server
        getCommand("banall").setExecutor(new BanAllCommand());
        getCommand("config").setExecutor(new ConfigCommand());
        getCommand("kickall").setExecutor(new KickAllCommand());
        getCommand("report").setExecutor(new ReportCommand());
        getCommand("staffchat").setExecutor(new StaffChatCommand());

        //Setter
        getCommand("setlink").setExecutor(new SetLinkCommand());
        getCommand("setprefix").setExecutor(new SetPrefixCommand());

        //Storing
        getCommand("backpackstored").setExecutor(new BackpackStoredCommand());
        getCommand("ffaarmor").setExecutor(new FFAArmorCommand());
        getCommand("ffainventory").setExecutor(new FFAInventoryCommand());
        getCommand("restoreinventory").setExecutor(new RestoreInventoryCommand());
        getCommand("storeinventory").setExecutor(new StoreInventoryCommand());

        //World
        getCommand("day").setExecutor(new DayCommand());
        getCommand("forceloadchunk").setExecutor(new ForceLoadChunkCommand());
        getCommand("getentities").setExecutor(new GetEntitesCommand());
        getCommand("killradius").setExecutor(new KillRadiusCommand());
        getCommand("navigator").setExecutor(new NavigatorCommand());
        getCommand("night").setExecutor(new NightCommand());
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

        if (!newDir.exists()) dir.mkdirs();

        for (File file : Objects.requireNonNull(dir.listFiles())) {
            try {
                Files.copy(dir.toPath(), newDir.toPath());
            }
            catch (IOException ignored) { }
        }
    }
}
