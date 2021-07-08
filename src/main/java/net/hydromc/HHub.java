package net.hydromc;

import net.hydromc.clickEvents.HubItemsClickEvent;
import net.hydromc.commands.ReloadCommand;
import net.hydromc.commands.SetSpawnCommand;
import net.hydromc.commands.SetVoidCommand;
import net.hydromc.commands.SpawnCommand;
import net.hydromc.feature.BoostPads;
import net.hydromc.feature.MOTDMessage;
import net.hydromc.feature.ScoreboardA;
import net.hydromc.feature.VoidTeleport;
import net.hydromc.gui.ServerSelectorGUI;
import net.hydromc.onJoin.HubItems;
import net.hydromc.onJoin.JoinMessage;
import net.hydromc.onJoin.JoinTitle;
import net.hydromc.placeholders.ServerNamePlaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.logging.Level;

public final class HHub extends JavaPlugin implements Listener {

    public Permission permission;

    public static String pluginPrefix;

    public static String noPermission;


    public HHub() {

        noPermission = "&cYou Have No Permissions To Do This!";

        // Admin Permissions!
        this.permission = new Permission("hhub.admin.reload");

        this.permission = new Permission("hhub.admin.features.voidtp");

        this.permission = new Permission("hhub.admin.spawn.set");

        this.permission = new Permission("hhub.admin.items.bypass");


        // Member Permissions!
        this.permission = new Permission("hhub.member.basic.servers");

        this.permission = new Permission("HHub.member.basic.gui.joinServers");

        this.permission = new Permission("hhub.member.basic.commands.spawn");


        // Pulling From Config
        pluginPrefix = this.getConfig().getString("pluginPrefix");

    }


    @Override
    public void onEnable() {

        // Permissions
        final PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.addPermission(this.permission);


        // Command Registering Here
        Objects.requireNonNull(getCommand("hhubreload")).setExecutor((new ReloadCommand(this)));

        Objects.requireNonNull(getCommand("servers")).setExecutor((new ServerSelectorGUI(this)));

        Objects.requireNonNull(getCommand("setvoidtp")).setExecutor((new SetVoidCommand(this)));

        Objects.requireNonNull(getCommand("setspawn")).setExecutor((new SetSpawnCommand(this)));

        Objects.requireNonNull(getCommand("spawn")).setExecutor((new SpawnCommand(this)));


        // Event Registering Goes Here!
        pluginManager.registerEvents(this, this);
        pluginManager.registerEvents(new JoinMessage(this), this);
        pluginManager.registerEvents(new HubItems(this), this);
        pluginManager.registerEvents(new JoinTitle(this), this);
        pluginManager.registerEvents(new MOTDMessage(this), this);
        pluginManager.registerEvents(new ScoreboardA(this), this);
        pluginManager.registerEvents(new HubItemsClickEvent(this), this);
        pluginManager.registerEvents(new VoidTeleport(this), this);
        pluginManager.registerEvents(new SetSpawnCommand(this), this);
        pluginManager.registerEvents(new BoostPads(this), this);

        //This registers our Custom PAPI Placeholders.

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new ServerNamePlaceHolder(this).register();
        }


        // Config Things.
        this.saveDefaultConfig();
        this.reloadConfig();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        createCustomConfig();


        getLogger().info("Has Been Enabled, G'Day Mate!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Has Been Disabled, Goodbye :(");
    }

    private File posConfFile;
    private FileConfiguration posConfig;

    public FileConfiguration getPosConfig() {
        return this.posConfig;
    }

    public void savePosConfig() {
        if (this.posConfig == null) {
            return;
        }

        try {
            this.getPosConfig().save(this.posConfFile);
        } catch (IOException e) {
            getLogger().info(String.valueOf(e));
        }
    }

    // MAINTENANCE CONFIG GENERATOR!
    public void reloadPosConfig() {
        if (this.posConfig == null) {
            createCustomConfig();
        }
        this.posConfig = YamlConfiguration.loadConfiguration(this.posConfFile);

        InputStream defaultStream = getResource("position.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.posConfig.setDefaults(defaultConfig);
        }
    }

    private void createCustomConfig() {
        posConfFile = new File(getDataFolder(), "position.yml");
        if (!posConfFile.exists()) {
            posConfFile.getParentFile().mkdirs();
            saveResource("position.yml", true);
        }

        posConfig = new YamlConfiguration();
        try {
            posConfig.load(posConfFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
