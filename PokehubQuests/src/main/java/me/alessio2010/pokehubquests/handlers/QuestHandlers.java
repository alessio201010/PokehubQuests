package me.alessio2010.pokehubquests.handlers;

import com.google.common.collect.Lists;
import me.alessio2010.pokehubquests.PokehubQuests;
import me.alessio2010.pokehubquests.configuration.players.PlayersConfigLayout;
import me.alessio2010.pokehubquests.configuration.quests.QuestConfigLayout;
import me.alessio2010.pokehubquests.configuration.quests.TypesOfQuest;
import me.alessio2010.pokehubquests.utils.messages.Placeholders;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestHandlers extends PlayersConfigLayout{

List<String> listToSet = Lists.newArrayList();
int playerProgress;
int xptogive;
String typeOfQuest;
int target;
List<String> activeQuests;
QuestConfigLayout activeQuestFile;
PlayersConfigLayout playerData;
String message;

        //HANDLE THE COMPLETION OF ANY QUEST
    public boolean handleQuestCompletion(Player player, QuestConfigLayout activeQuestFile, PlayersConfigLayout playerData, boolean globaltoggled) {
        target = activeQuestFile.getTarget();
        xptogive = activeQuestFile.getXpToGive();
        typeOfQuest = activeQuestFile.getTypeOfQuest();
            playerProgress = playerData.getQuestProgress().get(typeOfQuest).get(activeQuestFile.getIdentifier());
        if (playerProgress >= target) {
            message = PokehubQuests.getMessagesConfig().getPLAYER_COMPLETE_QUEST();
            PokehubQuests.getPlaceholders().handlePlaceholders(message, player, activeQuestFile);
            playerData.getActiveQuests().remove(activeQuestFile.getIdentifier());
            playerData.getCompletedQuests().add(activeQuestFile.getIdentifier());
            playerData.addXp(xptogive, player);
            activeQuestFile.giveRewardsList(player);
            if (globaltoggled) {
                activeQuestFile.sendGlobalCompletionMessage(player);
            }
              return true;
        } else {
            return false;
        }

    }

    public void handleBreakBlockQuest(BlockBreakEvent e, Player player) {

        //CHECK IF PLAYER HAS ANY ACTIVE QUESTS
        if (PokehubQuests.getPlayersconfig().getPlayerFiles().get(e.getPlayer().getUniqueId()).getActiveQuests().size() <= 0) {

            return;
        }
        playerData = PokehubQuests.getPlayersconfig().getPlayerFiles().get(player.getUniqueId());
        UUID uuid = player.getUniqueId();
        activeQuests = playerData.getActiveQuests();

          //HANDLE EACH QUEST INDIVIDUALLY AND ADD PROGRESS TO EM IF BLOCKS MATCH ETC
        activeQuests.forEach(activeQuest -> {
            activeQuestFile = PokehubQuests.getQuestConfig().getQuestFiles().get(activeQuest);
            typeOfQuest = activeQuestFile.getTypeOfQuest();
            if (!activeQuestFile.getTypeOfQuest().equalsIgnoreCase(TypesOfQuest.BREAK_BLOCK)) {return;}
            Material argsOfQuest = Material.getMaterial(activeQuestFile.getArgsOfQuest().replace("minecraft:", "").toUpperCase());
            if (e.getBlock().getType() != argsOfQuest) {return;}
            if (activeQuestFile.getTypeOfQuest().equalsIgnoreCase("BREAK_BLOCK")) {

            target = activeQuestFile.getTarget();


            playerProgress = playerData.getQuestProgress().get(typeOfQuest).get(activeQuestFile.getIdentifier());

            if (e.getBlock().getType() == argsOfQuest) {
                playerProgress++;
                playerData.getQuestProgress().get(activeQuestFile.getTypeOfQuest()).put(activeQuest, playerProgress);
                message = PokehubQuests.getMessagesConfig().getACTION_BAR_PROGRESS();
               message = message.replace("%quest_target%", String.valueOf(target));
               message = message.replace("%quest_name%", activeQuestFile.getDisplayName());
               message = message.replace("%player_quest_progress%", String.valueOf(playerProgress));

               MessageHandlers.sendActionBarMessage(player, message);
               if (handleQuestCompletion(player, activeQuestFile, playerData, activeQuestFile.isToggleGlobalCompletionMessage())) return;
            }
        }
        return;
        });
    }


    public QuestConfigLayout getQuestConfigLayout(String identifier) {
        return PokehubQuests.getQuestConfig().getQuestFiles().get(identifier);
    }

}
