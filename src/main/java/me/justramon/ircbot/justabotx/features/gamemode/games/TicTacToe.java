package me.justramon.ircbot.justabotx.features.gamemode.games;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.features.gamemode.IGame;

public class TicTacToe implements IGame
{
	@Override
	public void setup(String channel)
	{
		//TODO: Add setup stuff
	}
	
	@Override
	public void exeCommand(String cmdName, MessageEvent event, String[] args) throws Exception
	{
		// TODO: Add logic for commands of TicTacToe
	}

	@Override
	public String getName()
	{
		return "TicTacToe";
	}

	@Override
	public String getVersion()
	{
		return "1.0";
	}

	@Override
	public String[] getCommands()
	{
		// TODO: Add commands for TicTacToe, for example drawing your X or Y
		return null;
	}

}
