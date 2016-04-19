package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.Core;
import me.justramon.ircbot.justabotx.core.ICommand;

public class Quit implements ICommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		event.getBot().sendIRC().action(event.getChannel().getName(), "cries");
		Thread.sleep(2000);
		event.getChannel().send().message("Really?");
		Thread.sleep(2000);
		event.getChannel().send().message("...");
		Thread.sleep(1000);
		event.getChannel().send().message("bye guys :(");
		Core.bot.sendIRC().quitServer();
		System.exit(1);
	}

	@Override
	public String[] getAliases()
	{
		return new String[] {"stop", "quit", "goaway"};
	}

	@Override
	public String getInfo()
	{
		return "Stops the bot.";
	}
	
	@Override
	public boolean xtraFunc()
	{
		return false;
	}
	
	@Override
	public boolean isOperatorCommand()
	{
		return true;
	}

}
