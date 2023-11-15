package me.alessio2010.pokehubquests.listeners;

import me.alessio2010.pokehubquests.PokehubQuests;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        PokehubQuests.getPlayersconfig().writeToPlayerFile(e.getPlayer().getUniqueId()); //write all changes to player file
        PokehubQuests.playersconfig.getPlayerFiles().remove(e.getPlayer().getUniqueId()); //remove player from cache
    }
}

