package net.hydromc.onJoin;

import net.hydromc.HHub;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class HubItems implements Listener {

    private final HHub plugin;
    public HubItems(HHub plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemStack blaze = new ItemStack(Material.BLAZE_ROD);
        ItemStack book = new ItemStack(Material.BOOK);

        PlayerInventory playerInventory = p.getInventory();

        playerInventory.setItem(1, compass);
        playerInventory.setItem(4, blaze);
        playerInventory.setItem(8, book);

    }

}
