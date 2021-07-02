package net.hydromc;

import net.hydromc.feature.MOTDMessage;
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

public final class HHub extends JavaPlugin implements Listener {

    public Permission permission;

    public static String pluginPrefix;


    public HHub() {

        // Admin Permissions!
        this.permission = new Permission("hhub.admin.reload");


        // Member Permissions!
        // None yet. This is for spacing.


        // Pulling From Config
        pluginPrefix = this.getConfig().getString("pluginPrefix");

    }


    @Override
    public void onEnable() {

        // Permissions
        final PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.addPermission(this.permission);


        // Command Registering Here
        //Objects.requireNonNull(getCommand("whatever")).setExecutor((new yes()));


        // Event Registering Goes Here!
        pluginManager.registerEvents(this, this);
        pluginManager.registerEvents(new JoinMessage(this), this);
        pluginManager.registerEvents(new HubItems(this), this);
        pluginManager.registerEvents(new JoinTitle(this), this);
        pluginManager.registerEvents(new MOTDMessage(this), this);


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

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(cmd.getName().equalsIgnoreCase("hhubreload")) {

            if(sender.hasPermission("hhub.admin.reload")) {

                this.saveDefaultConfig();
                this.reloadConfig();
                getConfig().options().copyDefaults();
                saveDefaultConfig();

                sender.sendMessage(format(pluginPrefix + "&aAll Config Files Have Been Reloaded."));
                return true;

            } else {

                sender.sendMessage(format(pluginPrefix + "&cYou Have No Permissions Todo This."));
                return true;

            }
        }
        return true;
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
