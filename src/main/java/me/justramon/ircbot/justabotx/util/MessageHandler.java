package me.justramon.ircbot.justabotx.util;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.features.Logging;

public class MessageHandler
{
	public static void sendChannelMessage(MessageEvent event, String msg)
	{
		event.getChannel().send().message(msg);
		Logging.logMyChannelMessage(event, msg);
	}
	
	public static void respond(MessageEvent event, String msg)
	{
		event.respond(msg);
		Logging.logMyRespondMessage(event, msg);
	}
	
	public static void channelAction(MessageEvent event, String action)
	{
		event.getBot().sendIRC().action(event.getChannel().getName(), action);
		Logging.logMyAction(event, action);
	}
}
