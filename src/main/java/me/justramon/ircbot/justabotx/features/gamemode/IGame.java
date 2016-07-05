package me.justramon.ircbot.justabotx.features.gamemode;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.util.MessageHandler;

public interface IGame
{
	public default void setup(String channel) throws Exception
	{
		MessageHandler.sendChannelMessage(channel, getName() + " v" + getVersion());
		restart(channel);
	}
	
	public  void restart(String channel) throws Exception;
	
	public void exeCommand(String cmdName, MessageEvent event, String[] args) throws Exception;
	
	public String getName();
	
	public String getVersion();
	
	public String[] getCommands();
}
