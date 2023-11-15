package me.alessio2010.pokehubquests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.tools.javac.Main;
import me.alessio2010.pokehubquests.commands.BaseCommand;
import me.alessio2010.pokehubquests.configuration.MainConfig;
import me.alessio2010.pokehubquests.configuration.MessagesConfig;
import me.alessio2010.pokehubquests.configuration.levels.Level;
import me.alessio2010.pokehubquests.configuration.players.PlayersConfig;
import me.alessio2010.pokehubquests.configuration.players.PlayersConfigLayout;
import me.alessio2010.pokehubquests.configuration.quests.QuestConfig;
import me.alessio2010.pokehubquests.configuration.quests.QuestConfigLayout;
import me.alessio2010.pokehubquests.configuration.quests.TypesOfQuest;
import me.alessio2010.pokehubquests.handlers.QuestHandlers;
import me.alessio2010.pokehubquests.listeners.JoinEvent;
import me.alessio2010.pokehubquests.listeners.LeaveEvent;
import me.alessio2010.pokehubquests.listeners.questlisteners.QuestListeners;
import me.alessio2010.pokehubquests.utils.messages.Placeholders;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class PokehubQuests extends JavaPlugin {


    public static File dataFolder = new File("./plugins/pokehubquests/");


    public static PokehubQuests INSTANCE;
    public static MessagesConfig messagesConfig = new MessagesConfig();
    public static QuestConfig questConfig = new QuestConfig();
    public static QuestConfigLayout questConfigLayout = new QuestConfigLayout();
    public static MainConfig mainConfig = new MainConfig();
    public static PlayersConfig playersconfig = new PlayersConfig();
    public static PlayersConfigLayout playersConfigLayout = new PlayersConfigLayout();
    public static Level levelConfig = new Level();
    public static QuestHandlers questHandlers = new QuestHandlers();
    public static TypesOfQuest typesOfQuest = new TypesOfQuest();
    public static Placeholders placeholders = new Placeholders();

    public static MessagesConfig getMessagesConfig() {return messagesConfig;}
    public static QuestConfig getQuestConfig() {return questConfig;}
    public static PlayersConfig getPlayersconfig() {return playersconfig;}
    public static Level getLevelConfig() {return levelConfig;}
    public static QuestHandlers getQuestHandlers() {return questHandlers;}
    public static MainConfig getMainConfig() {return mainConfig;}
    public static TypesOfQuest getTypesOfQuest() {return typesOfQuest;}
    public static Placeholders getPlaceholders() {return placeholders;}



    @Override
    public void onEnable() {
        this.getCommand("quests").setExecutor(new BaseCommand());
        Bukkit.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new LeaveEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new QuestListeners(), this);
        getQuestConfig().handleQuestConfig();

        try {
            getLevelConfig().handleLevelsFile();
            handleMessagesConfig();
            handleMainConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void onDisable() {
       for (Player player : Bukkit.getServer().getOnlinePlayers()) {
           playersconfig.writeToPlayerFile(player.getUniqueId());
       }
    }

    public static void handleMainConfig() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File defaultConfigFile = new File(dataFolder, "config.json");

        if (defaultConfigFile.exists()) {
            try (FileReader fileReader = new FileReader(defaultConfigFile)) {
                MainConfig mainConfig = gson.fromJson(fileReader, MainConfig.class);
                Bukkit.getServer().getLogger().info("SUCCESSFULLY LOADED MAIN CONFIG");
            } catch (Exception e) {
                Bukkit.getServer().getLogger().warning("FAILED TO READ MAIN CONFIG");
                e.printStackTrace();
            }
        } if (!defaultConfigFile.exists()) {
            defaultConfigFile.createNewFile();
            MainConfig mainConfig = new MainConfig();
            try (FileWriter fileWriter = new FileWriter(defaultConfigFile)) {
                gson.toJson(mainConfig, fileWriter);
            } catch (Exception e) {
                Bukkit.getServer().getLogger().warning("FAILED TO CREATE DEFAULT MESSAGES CONFIG");
                e.printStackTrace();
            }

        }

    }

    public static void handleMessagesConfig() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File defaultMessagesFile = new File(dataFolder, "messages.json");

        if (defaultMessagesFile.exists()) {
            try (FileReader fileReader = new FileReader(defaultMessagesFile)) {
                MessagesConfig messagesConfig = gson.fromJson(fileReader, MessagesConfig.class);
                Bukkit.getServer().getLogger().info("SUCCESSFULLY LOADED MESSAGES CONFIG");
            } catch (Exception e) {
                Bukkit.getServer().getLogger().warning("FAILED TO READ MESSAGES CONFIG");
                e.printStackTrace();
            }
        } if (!defaultMessagesFile.exists()) {
            defaultMessagesFile.createNewFile();
            MessagesConfig messagesConfig = new MessagesConfig();
            try (FileWriter fileWriter = new FileWriter(defaultMessagesFile)) {
                gson.toJson(messagesConfig, fileWriter);
            } catch (Exception e) {
                Bukkit.getServer().getLogger().warning("FAILED TO CREATE DEFAULT MESSAGES CONFIG");
                e.printStackTrace();
            }

        }

    }


}
