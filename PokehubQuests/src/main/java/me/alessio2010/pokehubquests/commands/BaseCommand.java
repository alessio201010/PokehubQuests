package me.alessio2010.pokehubquests.commands;

import me.alessio2010.pokehubquests.PokehubQuests;
import me.alessio2010.pokehubquests.commands.subcommands.Reload;
import me.alessio2010.pokehubquests.commands.subcommands.StartQuest;
import me.alessio2010.pokehubquests.configuration.levels.LevelInfo;
import me.alessio2010.pokehubquests.configuration.quests.QuestConfigLayout;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Arrays;

public class BaseCommand implements CommandExecutor {

StartQuest startQuestController = new StartQuest();
Reload reloadCommand = new Reload();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        if (args.length == 0) {
            return true;
        }
        String SubCommand = args[0].toLowerCase();

        switch (SubCommand) {

            case "start":
              startQuestController.playerStartQuest(args[1], player, player.getUniqueId());
                break;

            case "reload":
                try {
                    reloadCommand.reloadConfigs(player);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                break;


        }




        return true;
    }
}
