package net.hydromc.commands;

import net.hydromc.HHub;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetSpawnCommand implements CommandExecutor {
    private HHub plugin;
    public SetSpawnCommand(HHub plugin){
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("hhubreload")) {

            if (sender.hasPermission("hhub.admin.reload")) {
                sender.sendMessage("Test");
            }
        }

        return true;
    }
}
