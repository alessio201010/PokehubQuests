package me.alessio2010.pokehubquests.configuration.players;

import me.alessio2010.pokehubquests.PokehubQuests;
import me.alessio2010.pokehubquests.configuration.MessagesConfig;
import me.alessio2010.pokehubquests.configuration.levels.LevelInfo;
import me.alessio2010.pokehubquests.handlers.MessageHandlers;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class PlayersConfigLayout {

    String uuid;
    String playername;
    int xp;
    int playerQuestLevel;
    List<String> completedQuests;
    List<String> activeQuests;
    HashMap<String, HashMap<String, Integer>> activeQuestProgress;

    public PlayersConfigLayout() {

    }
    public PlayersConfigLayout(String uuid, String playername, int xp, int playerQuestLevel, List<String> completedQuests, List<String> activeQuests) {
        this.uuid = uuid;
        this.playername = playername;
        this.xp = xp;
        this.playerQuestLevel = playerQuestLevel;
        this.completedQuests = completedQuests;
        this.activeQuests = activeQuests;
        this.activeQuestProgress = new HashMap<String, HashMap<String, Integer>>();
        this.activeQuestProgress.put("BREAK_BLOCK", new HashMap<String, Integer>());
        this.activeQuestProgress.put("PLACE_BLOCK", new HashMap<String, Integer>());
    }

    public int getXp() {
        return xp;
    }
    public int getPlayerQuestLevel() {
        return playerQuestLevel;
    }
    public List<String> getActiveQuests() {
        return activeQuests;
    }
    public List<String> getCompletedQuests() {
        return completedQuests;
    }
    public HashMap<String, HashMap<String, Integer>> getQuestProgress() {
        return activeQuestProgress;
    }


    public int addXp(int amount, Player player) {
        LevelInfo levelInfo = PokehubQuests.getLevelConfig().getLevelInfo(this.playerQuestLevel + 1);
        this.xp = this.xp + amount;
        if (this.xp >= levelInfo.getXprequired()) {
            this.xp = this.xp - levelInfo.getXprequired();
            playerLevelUp(player);
        }
        String message = PokehubQuests.getMessagesConfig().getPLAYER_GAIN_XP();
        message = message.replaceAll("%xp_received%", String.valueOf(amount));
        message = message.replaceAll("%player_xp%", String.valueOf(this.xp));

        player.sendMessage(MessageHandlers.getColours(message));
        return xp;
    }
    public int playerLevelUp(Player player) {
        this.playerQuestLevel = this.playerQuestLevel + 1;
        LevelInfo levelInfo = PokehubQuests.getLevelConfig().getLevelInfo(this.playerQuestLevel);
        String levelname = levelInfo.getLevelname();
        String message = PokehubQuests.getMessagesConfig().getPLAYER_LEVEL_UP();
        message = message.replaceAll("%level_name%", levelname);
        player.sendMessage(MessageHandlers.getColours(message));
        PokehubQuests.getLevelConfig().runLevelupCommands(this.playerQuestLevel, player);
        return playerQuestLevel;
    }


   public int setXp(int xp) {
        this.xp = xp;
        return xp;
   }


   //CHECK IF PLAYER HAS AT LEAST ONE ACTIVE QUEST
    public boolean checkIfHasNoActiveQuests() {
        if (this.activeQuests.size() <= 0) {
            return true;
        }
        return true;
    }

}
