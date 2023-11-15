package me.alessio2010.pokehubquests.configuration.levels;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.alessio2010.pokehubquests.PokehubQuests;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Level {
    File levelsfolder = new File("./plugins/pokehubquests/");


    public HashMap<Integer, LevelInfo> levelsData = new HashMap<>();
    public Level() {}

    public HashMap<Integer, LevelInfo> getLevelsData() {return levelsData;}

    public void setLevelData(HashMap<Integer, LevelInfo> levelsData) {
        this.levelsData = levelsData;
        return;
    }

     public LevelInfo getLevelInfo(int level) {
        return levelsData.get(level);
     }
    public void runLevelupCommands(int level, Player player) {
        LevelInfo levelInfo = levelsData.get(level);
        ConsoleCommandSender console = Bukkit.getConsoleSender().getServer().getConsoleSender();
        for (String commands : levelInfo.commandsOnLevelup) {
            commands = commands.replaceAll("%player%", player.getName());
            Bukkit.dispatchCommand(console, commands);
        }
    }

    public void handleReloadCommand() {
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File levelsfile = new File(levelsfolder, "levels.json");
         if (levelsfile.exists()) {
             try (FileReader fileReader = new FileReader(levelsfile)) {
                 Type type = new TypeToken<HashMap<Integer, LevelInfo>>(){}.getType();
                 levelsData = gson.fromJson(fileReader, type);
             } catch (IOException e) {
                 e.printStackTrace();
             }


         }
    }

    public void handleLevelsFile() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File levelsfile = new File(levelsfolder, "levels.json");
        if (levelsfile.exists()) {

            try (FileReader fileReader = new FileReader(levelsfile)) {
                Type type = new TypeToken<HashMap<Integer, LevelInfo>>(){}.getType();
                levelsData = gson.fromJson(fileReader, type);
            } catch (Exception e) {Bukkit.getServer().getLogger().warning("FAILED TO READ LEVELS.JSON");}

        }
        if (!levelsfile.exists()) {
            LevelInfo levelInfo1 = new LevelInfo("level1", 1, 100, false, Lists.newArrayList(""));
            LevelInfo levelInfo2 = new LevelInfo("level2", 2, 250, false, Lists.newArrayList(""));
            LevelInfo levelInfo3 = new LevelInfo("level3", 3, 500, false, Lists.newArrayList(""));
            getLevelsData().put(1, levelInfo1);
            getLevelsData().put(2, levelInfo2);
            getLevelsData().put(3, levelInfo3);
              levelsfile.createNewFile();

            try (FileWriter fileWriter = new FileWriter(levelsfile)) {
                gson.toJson(getLevelsData(), fileWriter);



            }catch (Exception e) {Bukkit.getServer().getLogger().warning("FAILED TO CREATE DEFAULT LEVELS.JSON");}

        }

    }


}
