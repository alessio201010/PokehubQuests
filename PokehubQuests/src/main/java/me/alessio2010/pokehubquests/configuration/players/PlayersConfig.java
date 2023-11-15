package me.alessio2010.pokehubquests.configuration.players;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.alessio2010.pokehubquests.PokehubQuests;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.UUID;

public class PlayersConfig {

    File playersfolder = new File("./plugins/pokehubquests/players/");
    public HashMap<UUID, PlayersConfigLayout> playerFiles;

    public PlayersConfig() {
        this.playerFiles = new HashMap<>();
    }

    public HashMap<UUID, PlayersConfigLayout> getPlayerFiles() {
        return playerFiles;
    }
    public void setPlayerFile(HashMap<UUID, PlayersConfigLayout> playerFiles) {
        this.playerFiles = playerFiles;
    }

    public boolean checkIfPlayerIsLoaded(UUID uuid) {
        if (!getPlayerFiles().containsKey(uuid)) {
            return false;
        }
        if (getPlayerFiles().containsKey(uuid)) {
            return true;
        }
        return true;
    }

    public PlayersConfigLayout getPlayerFile(UUID uuid, PlayersConfigLayout playerToGet) {
        playerToGet = getPlayerFiles().get(uuid);
        return playerToGet;
    }



    public void writeToPlayerFile(UUID uuid) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File playerdatafile = new File(this.playersfolder + uuid.toString() + ".json");
        File dataFile = new File(this.playersfolder, uuid + ".json");
        PlayersConfigLayout layouttowrite = getPlayerFiles().get(uuid);

        try (FileWriter fileWriter = new FileWriter(dataFile)) {
            gson.toJson(layouttowrite, fileWriter);
        } catch (Exception e) {
            Bukkit.getServer().getLogger().warning("FAILED TO WRITE TO PLAYER FILE");
        }

    }


    //CREATION OF PLAYER FILE IF NON EXISTANT | USED IN JOIN EVENT

    public void handlePlayerFile(UUID uuid) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        this.playersfolder.mkdirs();
        File playerdatafile = new File(this.playersfolder, uuid.toString() + ".json");
        if (playerdatafile.exists()) {
            try (FileReader fileReader = new FileReader(playerdatafile)) {
                PlayersConfigLayout playerlayout = gson.fromJson(fileReader, PlayersConfigLayout.class);
                if (!getPlayerFiles().containsKey(uuid)) {
                    getPlayerFiles().put(uuid, playerlayout);
                }
            } catch (Exception e) {
                Bukkit.getServer().getLogger().warning("FAILED TO READ OR CREATE DATA FILE FOR PLAYER " + uuid.toString());
            }
            return;
        }
        if (!playerdatafile.exists()) {
            File dataFile = new File(this.playersfolder, uuid.toString() + ".json");
            try {
                if (!dataFile.exists()) {
                    dataFile.createNewFile();
                }
            }
            catch (Exception e) {
                Bukkit.getServer().getLogger().warning("FAILED TO CREATE DATA FILE FOR PLAYER " + uuid.toString());
            }
            Player player = Bukkit.getPlayer(uuid);
            PlayersConfigLayout playersConfigLayout = new PlayersConfigLayout(uuid.toString(), Bukkit.getPlayer(uuid).getName(), 0, 0, Lists.newArrayList(), Lists.newArrayList());
            try (FileWriter fileWriter = new FileWriter(dataFile)) {
                gson.toJson(playersConfigLayout, fileWriter);
                if (!getPlayerFiles().containsKey(uuid)) {
                    getPlayerFiles().put(uuid, playersConfigLayout);
                }
            } catch (Exception e) {Bukkit.getServer().getLogger().warning("Failed to create default file for " + uuid.toString());}






        }


    }



}
