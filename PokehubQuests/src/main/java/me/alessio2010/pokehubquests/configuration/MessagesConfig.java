package me.alessio2010.pokehubquests.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MessagesConfig {



    public String PLAYER_START_QUEST;
    public String QUEST_ALREADY_COMPLETED;
    public String ACTION_BAR_PROGRESS;
    public String PLAYER_ENTERED_UNKNOWN_QUEST;
    public String PLAYER_GAIN_XP;
    public String QUEST_ALREADY_STARTED;
    public String PLAYER_COMPLETE_QUEST;
    public String PLAYER_LEVEL_UP;
    public String PLUGIN_RELOAD;
    public String QUEST_CONFIG_RELOAD;
    public MessagesConfig() {


        this.PLAYER_LEVEL_UP = "&eCongratulations! Your quest level is now %level_name%";
        this.PLAYER_COMPLETE_QUEST = "&eYou have completed your quest &7%quest_name%";
        this.PLAYER_START_QUEST = "&eYou have successfully started the &7%quest_name%";
        this.QUEST_ALREADY_STARTED = "&cYou have reached the maximum amount of quests started at once!";
        this.PLAYER_GAIN_XP = "&eYou have received &7%xp_received% &eXP! Your total is now %player_xp%";
        this.PLAYER_ENTERED_UNKNOWN_QUEST = "&cThe quest name &e%quest_name%&c you have entered is invalid";
        this.ACTION_BAR_PROGRESS = "&3&l%quest_name% &8&l| &f%player_quest_progress% &7/ &e%quest_target%";
        this.QUEST_ALREADY_COMPLETED = "&cYou have already completed the quest you are trying to start!";
        this.PLUGIN_RELOAD = "&ePokehubquests has successfully been reloaded!";
        this.QUEST_CONFIG_RELOAD = "&eYou have successfully loaded the quest | &7%quest_name%";
    }





    public String getQUEST_CONFIG_RELOAD() {return QUEST_CONFIG_RELOAD;}
    public String getPLAYER_LEVEL_UP() {return PLAYER_LEVEL_UP;}
    public String getPLAYER_COMPLETE_QUEST() {return PLAYER_COMPLETE_QUEST;}
    public String getPLAYER_START_QUEST() {return PLAYER_START_QUEST;}
    public String getPLAYER_GAIN_XP() {return PLAYER_GAIN_XP;}
    public String getPLAYER_ENTERED_UNKNOWN_QUEST() {return PLAYER_ENTERED_UNKNOWN_QUEST;}
    public String getQUEST_ALREADY_STARTED() {return QUEST_ALREADY_STARTED;}
    public String getACTION_BAR_PROGRESS() {
         return ACTION_BAR_PROGRESS;
    }
    public String getQUEST_ALREADY_COMPLETED() {
         return QUEST_ALREADY_COMPLETED;
    }
    public String getPLUGIN_RELOAD() {
        return PLUGIN_RELOAD;
    }
}
