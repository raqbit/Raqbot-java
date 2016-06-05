package me.justramon.ircbot.justabotx.commands;

import java.lang.management.ManagementFactory;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.ICommand;
import me.justramon.ircbot.justabotx.util.MessageHandler;
import me.justramon.ircbot.justabotx.util.TimeParser;

public class About implements ICommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		MessageHandler.sendChannelMessage(event, "Bleep Bleep - \"I am an IRC-Bot created by JustRamon\"");
		MessageHandler.sendChannelMessage(event, "Uptime: " + Colors.BOLD + new TimeParser().lts(ManagementFactory.getRuntimeMXBean().getUptime(), "%s:%s:%s:%s"));
	}

	@Override
	public String[] setAliases()
	{
		return new String[] {"about"};
	}

	@Override
	public String setInfo()
	{
		return "Gives you some info about the bot.";
	}
	
	@Override
	public boolean xtraFunc()
	{
		return false;
	}

}
