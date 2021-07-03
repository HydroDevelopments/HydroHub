package net.hydromc.commands;

import net.hydromc.HHub;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.hydromc.HHub.pluginPrefix;

public class ReloadCommand implements CommandExecutor {
    private HHub plugin;
    public ReloadCommand(HHub plugin){
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(cmd.getName().equalsIgnoreCase("hhubreload")) {

            if(sender.hasPermission("hhub.admin.reload")) {

                plugin.getLogger().info("Reloading config.yml...");
                plugin.saveDefaultConfig();
                plugin.reloadConfig();
                plugin.getConfig().options().copyDefaults();
                plugin.saveDefaultConfig();
                plugin.getLogger().info("Reloaded config.yml!");

                // Pos Config
                plugin.getLogger().info("Reloading position.yml...");
                plugin.savePosConfig();
                plugin.reloadPosConfig();
                plugin.getLogger().info("Reloaded position.yml!");

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
