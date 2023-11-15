package me.alessio2010.pokehubquests.listeners;

import me.alessio2010.pokehubquests.PokehubQuests;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {



        PokehubQuests.playersconfig.handlePlayerFile(e.getPlayer().getUniqueId()); //load player file to cache
    }

}