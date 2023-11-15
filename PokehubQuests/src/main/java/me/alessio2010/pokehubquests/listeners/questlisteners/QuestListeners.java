package me.alessio2010.pokehubquests.listeners.questlisteners;

import com.google.common.collect.Lists;
import me.alessio2010.pokehubquests.PokehubQuests;
import me.alessio2010.pokehubquests.configuration.players.PlayersConfigLayout;
import me.alessio2010.pokehubquests.configuration.quests.QuestConfigLayout;
import me.alessio2010.pokehubquests.configuration.quests.TypesOfQuest;
import me.alessio2010.pokehubquests.handlers.MessageHandlers;
import me.alessio2010.pokehubquests.handlers.QuestHandlers;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;
import java.util.List;

public class QuestListeners implements Listener {
    QuestHandlers questHandlers = new QuestHandlers();
    @EventHandler
    public void break_block(BlockBreakEvent e) {
        questHandlers.handleBreakBlockQuest(e, e.getPlayer());
    }
    }


