package me.justramon.ircbot.raqbotx.features.gamemode;

import me.justramon.ircbot.raqbotx.util.MessageHandler;
import org.pircbotx.hooks.events.MessageEvent;

public interface IGame
{
    default void setup(String channel) throws Exception {
		MessageHandler.sendChannelMessage(channel, getName() + " v" + getVersion());
		restart(channel);
	}

    void restart(String channel) throws Exception;

    void exeCommand(String cmdName, MessageEvent event, String[] args) throws Exception;

    String getName();

    String getVersion();

    String[] getCommands();
}
