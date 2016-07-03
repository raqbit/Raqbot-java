package me.justramon.ircbot.justabotx.features.gamemode;

import org.pircbotx.hooks.events.MessageEvent;

public interface IGame
{
	public void exe(String cmdName, MessageEvent event, String[] args) throws Exception;
	
	public String getInfo();
	
	public String getName();
	
	public String[] getCommands();
}
