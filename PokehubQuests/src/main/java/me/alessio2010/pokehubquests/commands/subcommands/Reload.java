package me.alessio2010.pokehubquests.commands.subcommands;

import com.sun.tools.javac.Main;
import me.alessio2010.pokehubquests.PokehubQuests;
import me.alessio2010.pokehubquests.configuration.levels.Level;
import me.alessio2010.pokehubquests.configuration.players.PlayersConfig;
import me.alessio2010.pokehubquests.configuration.quests.QuestConfig;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Reload {





    public void reloadConfigs(Player player) throws IOException {
        PokehubQuests.getLevelConfig().handleReloadCommand();
        PokehubQuests.getQuestConfig().handleReloadCommand(player);
        PokehubQuests.handleMainConfig();
        PokehubQuests.handleMessagesConfig();
        player.sendMessage(PokehubQuests.getMessagesConfig().getPLUGIN_RELOAD());
    }

}
