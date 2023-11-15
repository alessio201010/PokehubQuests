package me.alessio2010.pokehubquests.configuration.quests;


import me.alessio2010.pokehubquests.handlers.MessageHandlers;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class QuestConfigLayout {



    String identifier;
    String displayName;
    String completedDisplayName;
    String displayItem;
    String completedDisplayItem;
    List<String> displayLore;
    List<String> completedDisplayLore;
    int requiredPlayerLevel;
    boolean toggleRequiredPlayerLevel;
    int xpToGive;
    int target;
    String typeOfQuest;
    String argsOfQuest;
    String globalCompletionMessage;
    boolean toggleGlobalCompletionMessage;
    List<String> rewardsList;

    public QuestConfigLayout() {}
    public QuestConfigLayout(String identifier, String displayName, String completedDisplayName, String displayItem, String completedDisplayItem, List<String> displayLore, List<String> completedDisplayLore, int requiredPlayerLevel, boolean toggleRequiredPlayerLevel, int xpToGive, int target, String typeOfQuest, String argsOfQuest, String globalCompletionMessage, boolean toggleGlobalCompletionMessage, List<String> rewardsList) {
       this.identifier = identifier;
       this.displayName = displayName;
       this.completedDisplayName = completedDisplayName;
       this.displayItem = displayItem;
       this.completedDisplayItem = completedDisplayItem;
       this.displayLore = displayLore;
       this.completedDisplayLore = completedDisplayLore;
       this.requiredPlayerLevel = requiredPlayerLevel;
       this.toggleRequiredPlayerLevel = toggleRequiredPlayerLevel;
       this.xpToGive = xpToGive;
       this.target = target;
       this.typeOfQuest = typeOfQuest;
       this.argsOfQuest = argsOfQuest;
        this.rewardsList = rewardsList;
        this.globalCompletionMessage = globalCompletionMessage;
        this.toggleGlobalCompletionMessage = toggleGlobalCompletionMessage;
    }
    public String getIdentifier() {
        return identifier;
    }
    public String getDisplayName() {
        return displayName;
    }

   public String getGlobalCompletionMessage() {
        return globalCompletionMessage;
   }

    public boolean isToggleGlobalCompletionMessage() {
        return toggleGlobalCompletionMessage;
    }
   public List<String> getRewardsList() {
        return rewardsList;
   }
   public int getXpToGive() {
        return xpToGive;
   }
    public String getTypeOfQuest() {
        return typeOfQuest;
    }
    public int getTarget() {
        return target;
    }
    public String getArgsOfQuest() {
        return argsOfQuest;
    }

    public void giveRewardsList(Player player) {
        List<String> rewards = this.rewardsList;
        ConsoleCommandSender console = Bukkit.getConsoleSender().getServer().getConsoleSender();
        for (String rewardsind : rewards) {
            rewardsind = rewardsind.replaceAll("%player_name%", player.getName());
            Bukkit.dispatchCommand(console, rewardsind);
        }
    }

    public void sendGlobalCompletionMessage(Player playerWhoCompleted) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            String message = this.globalCompletionMessage.replaceAll("%player_name%", playerWhoCompleted.getName());
            message = message.replaceAll("%quest_name%", this.displayName);
            player.sendMessage(MessageHandlers.getColours(message));
        }
    }

}
