package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.util.IDevCommand;
import me.justramon.ircbot.justabotx.util.MessageHandler;

public class TestCommand implements IDevCommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		MessageHandler.respond(event, "Test succeeded.");
	}

	@Override
	public String[] setAliases()
	{
		return new String[] {"testcmd"};
	}
}
