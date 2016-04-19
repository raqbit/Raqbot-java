package me.justramon.ircbot.justabotx.core;

import org.apache.commons.lang3.time.StopWatch;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.DisconnectEvent;

import me.justramon.ircbot.justabotx.config.ConfigHandler;

public class ConnectionHandler extends ListenerAdapter
{
	public static StopWatch uptime = new StopWatch();
	
	public void onConnect(ConnectEvent event)
	{
		uptime.start();
		
		if(!Core.dev)
		{
			for(String s : ConfigHandler.config.channels)
			{
				Core.bot.sendIRC().joinChannel(s);
			}
		}
	}
	
	public void onDisconnect(DisconnectEvent event)
	{
		uptime.stop();
	}
}
