package net.hydromc.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.hydromc.HHub;
import org.bukkit.entity.Player;

public class ServerNamePlaceHolder extends PlaceholderExpansion {

    private HHub plugin;

    public void ServerNamePlaceHolder(HHub plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return "jiggey";
    }

    @Override
    public String getIdentifier(){
        return "hhub";
    }

    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier){

        if(player == null){
            return "";
        }

        if(identifier.equals("servername")){
            return plugin.getConfig().getString("serverName");
        }

        return null;
    }
}
