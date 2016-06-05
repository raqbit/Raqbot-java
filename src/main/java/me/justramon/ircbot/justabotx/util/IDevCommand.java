package me.justramon.ircbot.justabotx.util;

import org.pircbotx.hooks.Event;

public interface IDevCommand<M extends Event>
{
	public void exe(M event, String[] args) throws Exception;
	
	public String[] setAliases();
}
