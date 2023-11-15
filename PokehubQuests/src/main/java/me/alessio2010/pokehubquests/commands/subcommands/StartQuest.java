package me.alessio2010.pokehubquests.commands.subcommands;

import com.google.common.collect.Lists;
import me.alessio2010.pokehubquests.PokehubQuests;
import me.alessio2010.pokehubquests.configuration.players.PlayersConfigLayout;
import me.alessio2010.pokehubquests.configuration.quests.QuestConfig;
import me.alessio2010.pokehubquests.configuration.quests.QuestConfigLayout;
import me.alessio2010.pokehubquests.handlers.MessageHandlers;
import me.alessio2010.pokehubquests.handlers.QuestHandlers;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class StartQuest {


    public void playerStartQuest(String questname, Player player, UUID uuid) {

        //CHECK IF ALREADY COMPLETED
        if (PokehubQuests.getPlayersconfig().getPlayerFiles().get(uuid).getCompletedQuests().contains(questname)) {
            String message = PokehubQuests.getMessagesConfig().getQUEST_ALREADY_COMPLETED();
            player.sendMessage(MessageHandlers.getColours(message));
            return;
        }

        //CHECK IF THE QUEST ENTERED IS UNKNOWN
        if (!PokehubQuests.getQuestConfig().getAllAvailableQuestsIdentifiers().contains(questname)) {
            String message = MessageHandlers.getColours(PokehubQuests.getMessagesConfig().getPLAYER_ENTERED_UNKNOWN_QUEST());
            message = message.replace("%quest_name%", questname);
            player.sendMessage(message);
            return;
        }

        //CHECK IF THE PLAYER HAS REACHED THE MAXIMUM ALLOWED QUESTS
    if (PokehubQuests.getPlayersconfig().getPlayerFiles().get(uuid).getActiveQuests().size() >= PokehubQuests.getMainConfig().getMAX_QUESTS_STARTED_AT_SAME_TIME()) {
        player.sendMessage(MessageHandlers.getColours(PokehubQuests.getMessagesConfig().getQUEST_ALREADY_STARTED()));
        return;
    }

        if (!PokehubQuests.getPlayersconfig().getPlayerFiles().get(uuid).getActiveQuests().contains(questname)) {
            if (PokehubQuests.getQuestConfig().getAllAvailableQuestsIdentifiers().contains(questname)) {


                QuestConfigLayout layoutto = PokehubQuests.getQuestConfig().getQuestFiles().get(questname);
                String typeOfQuest = layoutto.getTypeOfQuest().toUpperCase();

                PlayersConfigLayout playerToGet = PokehubQuests.getPlayersconfig().getPlayerFiles().get(uuid);

                if (!playerToGet.getActiveQuests().contains(questname)) {
                    playerToGet.getActiveQuests().add(questname);
                    playerToGet.getQuestProgress().get(typeOfQuest).put(questname, 0);
                    PokehubQuests.getPlaceholders().handlePlaceholders(PokehubQuests.getMessagesConfig().getPLAYER_START_QUEST(), player, layoutto);
                    player.sendMessage(MessageHandlers.getColours(PokehubQuests.getMessagesConfig().getPLAYER_START_QUEST().replaceAll("%quest_name%", PokehubQuests.getQuestConfig().getQuestFiles().get(questname).getDisplayName())));
                    }
                    return;
                }
            }
        }
    }





