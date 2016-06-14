package me.justramon.ircbot.justabotx.features.gamemode;

import java.util.LinkedList;

import me.justramon.ircbot.justabotx.config.ConfigHandler;
import me.justramon.ircbot.justabotx.core.Core;
import me.justramon.ircbot.justabotx.features.gamemode.games.TestGame;

public class GameModeHandler
{
	private static LinkedList<String> chansPlaying = new LinkedList<String>();
	public static LinkedList<IGame> games = new LinkedList<IGame>();

	public GameModeHandler()
	{
		games.add(new TestGame());
	}
	
	public static boolean isPlaying(String channel)
	{
		if(!(chansPlaying == null))
		{
			for(String s : chansPlaying)
			{
				if(channel.equals(s))
					return true;
			}
		}
		return false;
	}

	public static void enableGameMode(String channel, String game)
	{
		if(hasGameModeEnabled(channel))
		{
			chansPlaying.add(channel);
			Core.bot.sendIRC().message(channel, "GameMode enabled.");
		}
		else
			Core.bot.sendIRC().message(channel, "This channel does not have GameMode enabled.");
	}

	public static void disableGameMode(String channel)
	{
		chansPlaying.remove(channel);
		Core.bot.sendIRC().message(channel, "GameMode disabled.");
	}

	private static boolean hasGameModeEnabled(String channel)
	{
		for(String s : ConfigHandler.config.gameChannels)
		{
			if(channel.equals(s))
				return true;
		}
		return false;
	}
}
