package me.alessio2010.pokehubquests.utils.messages;

import me.alessio2010.pokehubquests.configuration.levels.Level;
import me.alessio2010.pokehubquests.configuration.levels.LevelInfo;
import me.alessio2010.pokehubquests.configuration.players.PlayersConfigLayout;
import me.alessio2010.pokehubquests.configuration.quests.QuestConfigLayout;
import me.alessio2010.pokehubquests.handlers.MessageHandlers;
import org.bukkit.entity.Player;

public class Placeholders {


    public String PLAYERNAME_PLACEHOLDER = "%player_name%";
    public String PLAYER_XP_PLACEHOLDER = "%player_xp%";
    public String ACTIVEQUESTNAME_PLACEHOLDER = "%player_active_quest%";

    public String QUESTNAME_PLACEHOLDER = "%quest_name%";
    public String PLAYER_LEVEL = "%player_level%";
    public String NEXT_LEVEL_NAME;
    public String XP_RECEIVED = "%xp_received%";


    public Placeholders() {


    }

 public void handlePlaceholders(String message, Player player, QuestConfigLayout activeQuestFile, PlayersConfigLayout playerData, LevelInfo levelInfo) {
        message = message.replaceAll(this.QUESTNAME_PLACEHOLDER, activeQuestFile.getDisplayName());
        message = message.replaceAll(this.PLAYERNAME_PLACEHOLDER, player.getName());
        message = message.replaceAll(this.XP_RECEIVED, String.valueOf(activeQuestFile.getXpToGive()));
        message = message.replaceAll(this.PLAYER_XP_PLACEHOLDER, String.valueOf(playerData.getXp()));
        message = message.replaceAll(this.PLAYER_LEVEL, levelInfo.getLevelname());
        player.sendMessage(MessageHandlers.getColours(message));
}
    public void handlePlaceholders(String message, Player player, QuestConfigLayout activeQuestFile, LevelInfo levelInfo) {
        message = message.replaceAll(this.QUESTNAME_PLACEHOLDER, activeQuestFile.getDisplayName());
        message = message.replaceAll(this.PLAYERNAME_PLACEHOLDER, player.getName());
        message = message.replaceAll(this.XP_RECEIVED, String.valueOf(activeQuestFile.getXpToGive()));
        message = message.replaceAll(this.PLAYER_LEVEL, levelInfo.getLevelname());
        player.sendMessage(MessageHandlers.getColours(message));
    }
public void handlePlaceholders(String message, Player player, QuestConfigLayout activeQuestFile, PlayersConfigLayout playerData) {
    message = message.replaceAll(this.QUESTNAME_PLACEHOLDER, activeQuestFile.getDisplayName());
    message = message.replaceAll(this.PLAYERNAME_PLACEHOLDER, player.getName());
    message = message.replaceAll(this.XP_RECEIVED, String.valueOf(activeQuestFile.getXpToGive()));
    message = message.replaceAll(this.PLAYER_XP_PLACEHOLDER, String.valueOf(playerData.getXp()));
    player.sendMessage(MessageHandlers.getColours(message));
}
    public void handlePlaceholders(String message, Player player, QuestConfigLayout activeQuestFile) {
        message = message.replaceAll(this.QUESTNAME_PLACEHOLDER, activeQuestFile.getDisplayName());
        message = message.replaceAll(this.PLAYERNAME_PLACEHOLDER, player.getName());
        message = message.replaceAll(this.XP_RECEIVED, String.valueOf(activeQuestFile.getXpToGive()));
        player.sendMessage(MessageHandlers.getColours(message));
    }



    public void handlePlaceholders(String message, Player player, PlayersConfigLayout playerData) {
        message = message.replaceAll(this.PLAYERNAME_PLACEHOLDER, player.getName());
        message = message.replaceAll(this.PLAYER_XP_PLACEHOLDER, String.valueOf(playerData.getXp()));
        player.sendMessage(MessageHandlers.getColours(message));
    }
    public void handlePlaceholders(String message, Player player, PlayersConfigLayout playerData, LevelInfo levelInfo) {
        message = message.replaceAll(this.PLAYERNAME_PLACEHOLDER, player.getName());
        message = message.replaceAll(this.PLAYER_XP_PLACEHOLDER, String.valueOf(playerData.getXp()));
        message = message.replaceAll(this.PLAYER_LEVEL, levelInfo.getLevelname());
        player.sendMessage(MessageHandlers.getColours(message));
    }
    public void handlePlaceholders(String message, Player player) {
        message = message.replaceAll(this.PLAYERNAME_PLACEHOLDER, player.getName());
        player.sendMessage(MessageHandlers.getColours(message));
    }
    public void handlePlaceholders(String message, Player player, LevelInfo levelInfo) {
        message = message.replaceAll(this.PLAYERNAME_PLACEHOLDER, player.getName());
        message = message.replaceAll(this.PLAYER_LEVEL, levelInfo.getLevelname());
        player.sendMessage(MessageHandlers.getColours(message));
    }
}
