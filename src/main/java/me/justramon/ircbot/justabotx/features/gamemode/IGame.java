package me.justramon.ircbot.justabotx.features.gamemode;

import org.pircbotx.hooks.events.MessageEvent;

public interface IGame
{
	public void exe(MessageEvent event, String[] args) throws Exception;
	
	public String setInfo();
	
	public String[] setCommands();
}
