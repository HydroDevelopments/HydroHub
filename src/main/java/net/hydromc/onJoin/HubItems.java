package net.hydromc.onJoin;

import net.hydromc.HHub;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class HubItems implements Listener {

    private final HHub plugin;
    public HubItems(HHub plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        items(e.getPlayer());
    }

    public void items(Player p) {

        ItemStack compass = new ItemStack(Material.COMPASS);
        //ItemStack blaze = new ItemStack(Material.BLAZE_ROD);
        //ItemStack book = new ItemStack(Material.BOOK);

        ItemMeta compass_meta = compass.getItemMeta();
        compass_meta.setDisplayName(format("&3Server Selector"));
        ArrayList<String> compass_lore = new ArrayList();
        compass_lore.add(format("&dChoose A Server To Join!"));
        compass_meta.setLore(compass_lore);
        compass.setItemMeta(compass_meta);

        PlayerInventory playerInventory = p.getInventory();

        playerInventory.setItem(4, compass);
        //playerInventory.setItem(4, blaze);
        //playerInventory.setItem(7, book);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if(!p.isOp()) {
            e.setCancelled(true);
        } else if(p.isOp()) {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onInvMove(InventoryClickEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onInvMove(InventoryMoveItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDeath(PlayerRespawnEvent e) {
        items(e.getPlayer());
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
