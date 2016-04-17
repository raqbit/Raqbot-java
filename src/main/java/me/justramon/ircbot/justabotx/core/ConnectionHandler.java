package me.justramon.ircbot.justabotx.core;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;

import me.justramon.ircbot.justabotx.config.ConfigHandler;

public class ConnectionHandler extends ListenerAdapter<PircBotX>
{
	public void onConnect(ConnectEvent<PircBotX> event)
	{
		if(!Core.dev)
		{
			for(String s : ConfigHandler.config.channels)
			{
				Core.bot.sendIRC().joinChannel(s);
			}
		}
	}
}
