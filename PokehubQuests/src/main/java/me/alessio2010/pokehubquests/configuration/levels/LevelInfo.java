package me.alessio2010.pokehubquests.configuration.levels;

import java.util.List;

public class LevelInfo {

    String levelname;
    int level;
    int xprequired;
    boolean toggleCommandsOnLevelup;
    List<String> commandsOnLevelup;


    public LevelInfo() {}

    public LevelInfo(String levelname, int level, int xprequired, boolean toggleCommandsOnLevelup, List<String> commandsOnLevelup) {
        this.levelname = levelname;
        this.level = level;
        this.xprequired = xprequired;
        this.toggleCommandsOnLevelup = toggleCommandsOnLevelup;
        this.commandsOnLevelup = commandsOnLevelup;
    }

    public String getLevelname() {
        return levelname;
    }
    public int getLevel() {
        return level;
    }
    public int getNextLevel() {
        int nextlevel;
        nextlevel = level + 1;
        return nextlevel;
    }
    public int getXprequired() {
        return xprequired;
    }



}
