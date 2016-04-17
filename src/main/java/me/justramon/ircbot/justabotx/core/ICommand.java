package me.justramon.ircbot.justabotx.core;

import org.pircbotx.hooks.Event;

public interface ICommand<M extends Event>
{
	public void exe(M event) throws Exception;
	
	public String[] getAliases();
	
	public default String getUsage()
	{
		return "?<command>";
	}
	
	public String getInfo();
}
