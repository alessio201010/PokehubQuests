package me.alessio2010.pokehubquests.configuration;

public class MainConfig {


    public int MAX_QUESTS_STARTED_AT_SAME_TIME;


    public MainConfig() {
        this.MAX_QUESTS_STARTED_AT_SAME_TIME = 3;
    }

   public int getMAX_QUESTS_STARTED_AT_SAME_TIME() {
        return MAX_QUESTS_STARTED_AT_SAME_TIME;
   }
}
