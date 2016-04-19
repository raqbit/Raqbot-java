package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.ICommand;

public class About implements ICommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		event.getChannel().send().message("This bot is made by JustRamon.");
		event.getChannel().send().message("Uptime: " + Colors.BOLD + "[Coming Soon]");
	}

	@Override
	public String[] getAliases()
	{
		return new String[] {"about"};
	}

	@Override
	public String getInfo()
	{
		return "Gives you some info about the bot.";
	}

}
