package me.alessio2010.pokehubquests.handlers;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageHandlers {

    public static String getColours(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void sendActionBarMessage(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(MessageHandlers.getColours(message)));
    }





}
