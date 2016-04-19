package me.justramon.ircbot.justabotx.features;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.config.ConfigHandler;
import me.justramon.ircbot.justabotx.core.ICommand;

public class XtraFunc
{
	public static boolean hasXtraFuncEnabled(String channelName)
	{
		for(String channel : ConfigHandler.config.xtrafunc)
		{
			if(channel == channelName)
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean isExtraFuncCommand(ICommand<MessageEvent> command)
	{
		if(command.xtraFunc())
			return true;
		return false;
	}
}
