package me.justramon.ircbot.justabotx.util;

import org.pircbotx.hooks.Event;

public interface IDevCommand<M extends Event>
{
	public void exe(M event) throws Exception;
	
	public String[] getAliases();
}
