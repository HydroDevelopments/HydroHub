package net.hydromc.commands;

import net.hydromc.HHub;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import static net.hydromc.HHub.pluginPrefix;

public class SetSpawnCommand implements CommandExecutor, Listener {
    private HHub plugin;
    public SetSpawnCommand(HHub plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.teleport(player.getWorld().getSpawnLocation());
    }

    @EventHandler
    public void onJoin(PlayerRespawnEvent e) {
        Player player = e.getPlayer();
        player.teleport(player.getWorld().getSpawnLocation());
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("setspawn")) {

            if (sender.hasPermission("hhub.admin.spawn.set")) {
                Player p = (Player) sender;

                FileConfiguration c = plugin.getPosConfig();

                c.set("spawnPosX", p.getLocation().getX());
                c.set("spawnPosY", p.getLocation().getY());
                c.set("spawnPosZ", p.getLocation().getZ());

                c.set("spawnYaw", p.getLocation().getYaw());
                c.set("spawnPitch", p.getLocation().getPitch());

                p.sendMessage(format(pluginPrefix + "&eYou have set the world spawn to: &f " +p.getWorld().getSpawnLocation()));
            }
        }

        return true;
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
