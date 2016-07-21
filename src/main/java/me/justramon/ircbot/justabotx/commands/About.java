package me.justramon.ircbot.justabotx.commands;

import me.justramon.ircbot.justabotx.core.Core;
import me.justramon.ircbot.justabotx.core.ICommand;
import me.justramon.ircbot.justabotx.util.MessageHandler;
import me.justramon.ircbot.justabotx.util.TimeParser;
import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import java.lang.management.ManagementFactory;

public class About implements ICommand<MessageEvent>
{

	public void exe(MessageEvent event, String[] args) throws Exception
	{
		MessageHandler.sendChannelMessage(event, "Bleep Bleep - \"I am an IRC-Bot created by JustRamon\"");
		MessageHandler.sendChannelMessage(event, "version: " + Core.version);
		MessageHandler.sendChannelMessage(event, "Uptime: " + Colors.BOLD + new TimeParser().lts(ManagementFactory.getRuntimeMXBean().getUptime(), "%s:%s:%s:%s"));
	}

	public String[] getAliases()
	{
		return new String[] {"about"};
	}

	public String getInfo()
	{
		return "Gives you some info about the bot.";
	}
	
	public boolean xtraFunc()
	{
		return false;
	}

}
