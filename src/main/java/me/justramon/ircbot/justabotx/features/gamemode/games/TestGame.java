package me.justramon.ircbot.justabotx.features.gamemode.games;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.features.gamemode.IGame;
import me.justramon.ircbot.justabotx.util.MessageHandler;

public class TestGame implements IGame
{

	@Override
	public void exe(String cmdName, MessageEvent event, String[] args) throws Exception
	{
		if(cmdName.equals("test"))
		{
			MessageHandler.respond(event, "123");
		}
	}

	@Override
	public String getInfo()
	{
		return "Simple Test Game";
	}

	@Override
	public String[] getCommands()
	{
		return new String[]{"test"};
	}

}
