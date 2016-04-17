package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.ICommand;
import me.justramon.ircbot.justabotx.util.IDevCommand;

public class TestCommand implements IDevCommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event) throws Exception
	{
		event.respond("Test succeeded.");
	}

	@Override
	public String[] getAliases()
	{
		return new String[] {"testcmd"};
	}
}
