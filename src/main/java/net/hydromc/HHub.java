package net.hydromc;

import net.hydromc.clickEvents.HubItemsClickEvent;
import net.hydromc.commands.ReloadCommand;
import net.hydromc.feature.MOTDMessage;
import net.hydromc.feature.ScoreboardA;
import net.hydromc.gui.ServerSelectorGUI;
import net.hydromc.onJoin.HubItems;
import net.hydromc.onJoin.JoinMessage;
import net.hydromc.onJoin.JoinTitle;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class HHub extends JavaPlugin implements Listener {

    public Permission permission;

    public static String pluginPrefix;

    public static String noPermission;


    public HHub() {

        noPermission = "&cYou Have No Permissions To Do This!";

        // Admin Permissions!
        this.permission = new Permission("hhub.admin.reload");


        // Member Permissions!
        this.permission = new Permission("hhub.member.basic.servers");
        this.permission = new Permission("HHub.member.basic.gui.joinServers");


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


        // Event Registering Goes Here!
        pluginManager.registerEvents(this, this);
        pluginManager.registerEvents(new JoinMessage(this), this);
        pluginManager.registerEvents(new HubItems(this), this);
        pluginManager.registerEvents(new JoinTitle(this), this);
        pluginManager.registerEvents(new MOTDMessage(this), this);
        pluginManager.registerEvents(new ScoreboardA(this), this);
        pluginManager.registerEvents(new HubItemsClickEvent(this), this);


        // Config Things.
        this.saveDefaultConfig();
        this.reloadConfig();
        getConfig().options().copyDefaults();
        saveDefaultConfig();


        getLogger().info("Has Been Enabled, G'Day Mate!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Has Been Disabled, Goodbye :(");
    }

}
