package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.ICommand;

public class Source implements ICommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		event.respond("Find my source here: https://github.com/justramon/justabotx");
	}

	@Override
	public String[] getAliases()
	{
		return new String[] {"source", "github", "src"};
	}

	@Override
	public String getInfo()
	{
		return "Returns you the github link for the sourcecode of this bot.";
	}
	
	@Override
	public boolean xtraFunc()
	{
		return false;
	}

}
