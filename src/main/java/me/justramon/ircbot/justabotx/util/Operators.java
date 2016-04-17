package me.justramon.ircbot.justabotx.util;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.config.ConfigHandler;

public class Operators
{
	public static boolean isOp(MessageEvent event)
	{
		if(event.getUser().isVerified())
		{
			for(String s : ConfigHandler.config.operators)
			{
				if(event.getUser().getNick().equals(s))
				{
					return true;
				}
			}
			event.respond("You are not an operator!");
			return false;
		}
		else
		{
			event.respond("Sorry, but you're not verified.");
			return false;
		}
	}
}
