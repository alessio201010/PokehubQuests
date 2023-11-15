package me.alessio2010.pokehubquests.configuration.quests;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.alessio2010.pokehubquests.PokehubQuests;
import me.alessio2010.pokehubquests.handlers.MessageHandlers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class QuestConfig {
    public File dataFolder = new File("./plugins/pokehubquests/quests/");

    public List<String> availableQuests = Lists.newArrayList();
    public HashMap<String, QuestConfigLayout> questFiles = new HashMap<>();

    public HashMap<String, QuestConfigLayout> getQuestFiles() {
        return questFiles;
    }

    public File getQuestFile(String identifier, Player player){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (player == null) {return null;}
        if (!dataFolder.exists()) {player.sendMessage();}
     return null;
    }


    public void handleReloadCommand(Player player) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        if (dataFolder.exists()) {
            reloadAvailableQuestsIdentifiers();
            for (String filename : PokehubQuests.getQuestConfig().dataFolder.list()) {
                String filenamewithoutjson = filename.replace(".json", "");
                File convertedFile = new File("./plugins/pokehubquests/quests/" + filename);
                String message = PokehubQuests.getMessagesConfig().getQUEST_CONFIG_RELOAD();
                message = MessageHandlers.getColours(message);
                message = message.replace("%quest_name%", filename);
                try (FileReader fileReader = new FileReader(convertedFile)) {

                    QuestConfigLayout layout = gson.fromJson(fileReader, QuestConfigLayout.class);
                    Bukkit.getServer().getLogger().info("SUCCESSFULLY LOADED QUEST FILE | " + filename);
                    if (questFiles.containsKey(filenamewithoutjson)) {questFiles.remove(filenamewithoutjson);}


                    if (!questFiles.containsKey(filenamewithoutjson)) {

                        questFiles.put(filenamewithoutjson, layout);
                        player.sendMessage(message);
                        getAllAvailableQuestsIdentifiers();
                    }
                } catch (Exception e) {
                    Bukkit.getServer().getLogger().warning("FAILED TO LOAD IN ALL OF QUESTS FILES");

                    e.printStackTrace();
                }

            }

        }
    }

    public String getFileNameWithoutJson(String filename) {
        filename = filename.replace(".json", "");
        return filename;
    }

    public void reloadAvailableQuestsIdentifiers() {
        this.availableQuests.clear();
        for (String filename : dataFolder.list()) {
            this.availableQuests.add(getFileNameWithoutJson(filename));
        }
           return;
    }
    public List<String> getAllAvailableQuestsIdentifiers() {
        return availableQuests;
    }

    public void handleQuestConfig() {
        QuestConfigLayout defaultQuest1 = new QuestConfigLayout("mine100stone", "&6Mine 100 Stone", "&m&8Mine 100 Stone", "minecraft:stone", "minecraft:iron_bars", Lists.newArrayList("blablabla"), Lists.newArrayList("blablabla"), 0, false, 10, 100, "BREAK_BLOCK", "minecraft:stone", "&e%player_name% &7has completed their &f%quest_name%&7 and you can too via &f(&7&i/quests&f)", true, Lists.newArrayList("give %player_name% diamond 5"));
        QuestConfigLayout defaultQuest2 = new QuestConfigLayout("place100stone", "&6Place 100 Stone", "&m&8Place 100 Stone", "minecraft:stone", "minecraft:iron_bars", Lists.newArrayList("blablabla"), Lists.newArrayList("blablabla"), 0, false, 10, 100, "PLACE_BLOCK", "minecraft:stone", "&e%player_name% &7has completed their &f%quest_name%&7 and you can too via &f(&7&i/quests&f)", true, Lists.newArrayList("give %player_name% diamond 5"));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String defaultQuest1Identifier = defaultQuest1.identifier;
        String defaultQuest2Identifier = defaultQuest2.identifier;

        File defaultQuest1File = new File(dataFolder, defaultQuest1Identifier + ".json");
        File defaultQuest2File = new File(dataFolder, defaultQuest2Identifier + ".json");
        if (dataFolder.exists()) {
            reloadAvailableQuestsIdentifiers();
            for (String filename : PokehubQuests.getQuestConfig().dataFolder.list()) {
                String filenamewithoutjson = filename.replace(".json", "");
                File convertedFile = new File("./plugins/pokehubquests/quests/" + filename);
                try (FileReader fileReader = new FileReader(convertedFile)) {

                    QuestConfigLayout layout = gson.fromJson(fileReader, QuestConfigLayout.class);
                    Bukkit.getServer().getLogger().info("SUCCESSFULLY LOADED QUEST FILE | " + filename);
                    if (!questFiles.containsKey(filenamewithoutjson)) {
                        questFiles.put(filenamewithoutjson, layout);
                    }

                } catch (Exception e) {
                    Bukkit.getServer().getLogger().warning("FAILED TO LOAD IN ALL OF QUESTS FILES");

                    e.printStackTrace();
                }

            }
            //NEED TO SEE HOW TO LOOK THROUGH ALL FILES, GET THEIR FILE NAMES, ADD EM TO THE MAP

        }
        //DEFAULT FILE CREATION, THEN IT IS ALL TO CREATE FILES MANUALLY LOAD THEM IN CONFIG BY LOOPING
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
            String identifier1 = defaultQuest1.getIdentifier();
            String identifier2 = defaultQuest2.getIdentifier();
            if (!questFiles.containsKey(defaultQuest1Identifier) && !questFiles.containsKey(defaultQuest2Identifier)) {
                try {
                    if (!defaultQuest1File.exists() && !defaultQuest2File.exists()) {
                        defaultQuest1File.createNewFile();
                        defaultQuest2File.createNewFile();
                    }
                } catch (Exception e) {
                    Bukkit.getServer().getLogger().warning("FAILED TO CREATE DEFAULT CONFIG QUEST FOLDER");

                }
                try (FileWriter fileWriter = new FileWriter(defaultQuest1File)) {
                    gson.toJson(defaultQuest1, fileWriter);
                    if (!questFiles.containsKey(identifier1)) {
                        questFiles.put(identifier1, defaultQuest1);
                    }
                } catch (Exception e) {
                    Bukkit.getServer().getLogger().warning("FAILED TO DEFAULT QUEST1 FILE");
                }
                try (FileWriter fileWriter = new FileWriter(defaultQuest2File)) {
                    gson.toJson(defaultQuest2, fileWriter);
                    if (!questFiles.containsKey(identifier2)) {
                        questFiles.put(identifier2, defaultQuest2);
                    }
                } catch (Exception e) {
                    Bukkit.getServer().getLogger().warning("FAILED TO DEFAULT QUEST2 FILE");
                }
            }
        }
    }
    }



