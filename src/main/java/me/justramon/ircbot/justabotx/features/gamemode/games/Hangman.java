package me.justramon.ircbot.justabotx.features.gamemode.games;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.Core;
import me.justramon.ircbot.justabotx.features.gamemode.IGame;
import me.justramon.ircbot.justabotx.util.MessageHandler;

public class Hangman implements IGame
{
	private final ArrayList<String> usedUpLetters = new ArrayList<String>();
	private int hangman;
	private List<String> words;
	private String currentWord;
	private char[] guessed;
	private boolean finished;
	private boolean loaded;

	@Override
	public void setup(String channel) throws Exception
	{
		Core.bot.sendIRC().message(channel, "Use @guess letter to guess a letter. When you guess a wrong letter, your hangman will be built one step further.");
		Core.bot.sendIRC().message(channel, "If he is complete, you lose. If you guess the word before, you win. This is how the word looks:");
		restart(channel);
	}
	@Override
	public void restart(String channel) throws Exception
	{
		try
		{
			usedUpLetters.clear();
			hangman = 0;
			loaded = true;
			finished = false;
			words = FileUtils.readLines(new File("words.txt"));
			currentWord = words.get(new Random().nextInt(words.size()));
			guessed = new char[currentWord.length()];
			sendWord(channel);
		}
		catch(IOException e)
		{
			loaded = false;
			MessageHandler.sendChannelMessage(channel, "Word database could not be loaded.");
		}
	}

	@Override
	public void exeCommand(String cmdName, MessageEvent event, String[] args) throws Exception
	{
		if(finished)
		{
			Core.bot.sendIRC().message(event.getChannel().getName(), "This game has ended.");
			return;
		}
		
		if(loaded)
		{
			if(args.length == 1)
			{
				if(args[0].length() == 1)
				{
					if(Character.isLetter(args[0].charAt(0)))
					{
						if(!usedUpLetters.contains(args[0]))
						{
							useUp(event.getChannel().getName(), args[0]);
						}
						else
						{
							Core.bot.sendIRC().message(event.getChannel().getName(), "You already used that letter.");
						}
					}
					else
					{
						Core.bot.sendIRC().message(event.getChannel().getName(), "Please enter one valid letter.");
					}
				}
				else
				{
					Core.bot.sendIRC().message(event.getChannel().getName(), "Please enter one valid letter.");
				}
			}
			else
			{
				Core.bot.sendIRC().message(event.getChannel().getName(), "Please enter the letter you want to guess.");
			}
		}
		else
		{
			MessageHandler.sendChannelMessage(event, "Word database could not be loaded.");
		}
	}

	@Override
	public String getName()
	{
		return "Hangman";
	}

	@Override
	public String getVersion()
	{
		return "1.0";
	}

	@Override
	public String[] getCommands()
	{
		return new String[]{"guess"};
	}

	/**
	 * Disabled a character so it can't be clicked anymore
	 * @param character The character to disable (A-Z)
	 */
	private void useUp(String channel, String character)
	{
		character = character.toLowerCase();
		usedUpLetters.add(character);

		if(currentWord.contains(character))
		{
			char[] cA = currentWord.toCharArray();

			for(int i = 0; i < cA.length; i++)
			{
				if(character.equals("" + cA[i]))
				{
					guessed[i] = cA[i];
					sendWord(channel);
				}
			}

			if(Arrays.toString(guessed).replace("[", "").replace("]", "").replace(",", "").replace(" ", "").equals(currentWord))
			{
				finished = true;
				MessageHandler.sendChannelMessage(channel, "You won!");
			}
		}
		else if(!finished)
		{
			hangman++;
			sendWord(channel);
			sendHangman(channel);

			if(hangman == 11)
			{
				finished = true;
				MessageHandler.sendChannelMessage(channel, "You lost! The word was \"" + currentWord + "\".");
			}
		}
	}

	private void sendWord(String channel)
	{
		String output = "";

		for(char c : guessed)
		{
			if(c == 0)
				output += "_ ";
			else
				output += c + " ";
		}

		Core.bot.sendIRC().message(channel, output.trim());
	}

	private void sendHangman(String channel)
	{
		for(String s : hangmans[hangman - 1])
		{
			Core.bot.sendIRC().message(channel, s);
		}
	}
	
	//put down here for easier navigation
	private final String[][] hangmans = {
			{"_______"},
			{
				"   |",
				"   |",
				"   |",
				"   |",
				"   |",
				"___|___"
			},
			{
				"   |/",
				"   |",
				"   |",
				"   |",
				"   |",
				"___|___"
			},
			{
				"   _________",
				"   |/",
				"   |",
				"   |",
				"   |",
				"   |",
				"___|___"
			},
			{
				"   _________",
				"   |/      |",
				"   |",
				"   |",
				"   |",
				"   |",
				"___|___"
			},
			{
				"   _________",
				"   |/      |",
				"   |      (_)",
				"   |",
				"   |",
				"   |",
				"___|___"
			},
			{
				"   _________",
				"   |/      |",
				"   |      (_)",
				"   |       |",
				"   |       |",
				"   |",
				"___|___"
			},
			{
				"   _________",
				"   |/      |",
				"   |      (_)",
				"   |       |",
				"   |       |",
				"   |        \\",
				"___|___"
			},
			{
				"   _________",
				"   |/      |",
				"   |      (_)",
				"   |       |",
				"   |       |",
				"   |      / \\",
				"___|___"
			},
			{
				"   _________",
				"   |/      |",
				"   |      (_)",
				"   |      \\|",
				"   |       |",
				"   |      / \\",
				"___|___"
			},
			{
				"   _________",
				"   |/      |",
				"   |      (_)",
				"   |      \\|/",
				"   |       |",
				"   |      / \\",
				"___|___"
			},
	};
}
