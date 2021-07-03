package net.hydromc.commands;

import net.hydromc.HHub;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import static net.hydromc.HHub.pluginPrefix;

public class SetSpawnCommand implements CommandExecutor {
    private HHub plugin;
    public SetSpawnCommand(HHub plugin){
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("setspawn")) {

            if (sender.hasPermission("hhub.admin.spawn.set")) {
                Player p = (Player) sender;
                Location loc = p.getLocation();

                p.getWorld().setSpawnLocation(loc);

                p.sendMessage(format(pluginPrefix + "&eYou have set the world spawn to: &f " +p.getWorld().getSpawnLocation()));
            }
        }

        return true;
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
