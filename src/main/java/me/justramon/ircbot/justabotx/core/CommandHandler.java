package me.justramon.ircbot.justabotx.core;

import java.util.LinkedList;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import me.justramon.ircbot.justabotx.commands.*;
import me.justramon.ircbot.justabotx.features.XtraFunc;
import me.justramon.ircbot.justabotx.features.gamemode.GameModeHandler;
import me.justramon.ircbot.justabotx.features.gamemode.IGame;
import me.justramon.ircbot.justabotx.util.IDevCommand;
import me.justramon.ircbot.justabotx.util.MessageHandler;
import me.justramon.ircbot.justabotx.util.Operators;
import me.justramon.ircbot.justabotx.util.StringUtils;

public class CommandHandler extends ListenerAdapter
{
	public static LinkedList<ICommand<MessageEvent>> commands = new LinkedList<ICommand<MessageEvent>>();
	private LinkedList<IDevCommand<MessageEvent>> devcommands = new LinkedList<IDevCommand<MessageEvent>>();

	public CommandHandler()
	{
		commands.add(new Enable());
		commands.add(new Disable());
		commands.add(new Quit());
		commands.add(new Clear());
		commands.add(new Reload());
		commands.add(new Help());
		commands.add(new Request());
		commands.add(new ForDucksSake());
		commands.add(new Nick());
		commands.add(new Source());
		commands.add(new Nick());
		commands.add(new About());
		if(Core.dev)
		{
			devcommands.add(new ForceShow());
		}
	}

	public void onMessage(MessageEvent event) throws Exception
	{
		String[] args = StringUtils.trimArgrumentsFromCommand(event.getMessage().split(" "));
		String cmdName = event.getMessage().split(" ")[0];

		if(cmdName.startsWith("?"))
		{
			for (ICommand<MessageEvent> cmd : commands)
			{
				if (Core.enabled || cmd instanceof Enable || cmd instanceof Disable)
				{
					for (String s : cmd.getAliases())
					{
						if (cmdName.equalsIgnoreCase("?" + s))
						{
							if(XtraFunc.isExtraFuncCommand(cmd))
							{
								if(XtraFunc.hasXtraFuncEnabled(event.getChannel().getName()))
								{
									cmd.exe(event, args);
									System.gc();
									break;
								}
							}
							else
							{
								if(Operators.isOpCommand(cmd))
								{
									if(Operators.isOp(event))
									{
										cmd.exe(event, args);
										System.gc();
										break;
									}
								}
								else
								{
									cmd.exe(event, args);
									System.gc();
									break;
								}
							}
						}
					}
				}
			}
			if(Core.enabled)
			{
				for(IDevCommand<MessageEvent> cmd : devcommands)
				{
					for(String s : cmd.getAliases())
					{
						if (cmdName.equalsIgnoreCase("?" + s))
						{
							if(Operators.isOp(event))
							{
								cmd.exe(event, args);
								System.gc();
								break;
							}
						}
					}
				}
			}
		}
		else if(cmdName.startsWith("@") && Core.enabled)
		{
			String channel = event.getChannel().getName();

			if(cmdName.equalsIgnoreCase("@disable") && Operators.isOp(event))
			{
				if(GameModeHandler.isPlaying(channel))
				{
					GameModeHandler.disableGameMode(channel);
					System.gc();
				}
				else
				{	
					MessageHandler.respond(event, "This channel is currently not playing a game.");
				}
			}
			else if(cmdName.equalsIgnoreCase("@game") && Operators.isOp(event))
			{
				if(!GameModeHandler.isPlaying(channel))
				{
					if(args.length == 1)
					{
						boolean installed = false;
						for(IGame game : GameModeHandler.games)
						{
							if(args[0].equalsIgnoreCase(game.getName()))
							{
								installed = true;
								GameModeHandler.enableGameMode(channel, args[0]);
							}
						}
						if(!installed)
						{
							MessageHandler.respond(event, "That is not a valid game.");
							MessageHandler.sendChannelMessage(event, "Currently installed: " + GameModeHandler.listAllGames());
						}
					}
					else
					{
						MessageHandler.respond(event, "Please pass an installed game that you want to play.");
						MessageHandler.sendChannelMessage(event, "Currently installed: " + GameModeHandler.listAllGames());
					}
				}
				else
				{
					MessageHandler.respond(event, "This channel is already playing a game!");
				}
			}
			else if(GameModeHandler.isPlaying(channel))
			{
				boolean gamecmd = false;
				for(IGame game : GameModeHandler.games)
				{
					for(String s : game.getCommands())
					{
						if(GameModeHandler.currentlyPlaying.get(event.getChannel().getName()).equalsIgnoreCase(game.getName()))
						{
							if(cmdName.equalsIgnoreCase("@" + s))
							{
								gamecmd = true;
								game.exeCommand(cmdName, event, args);
								System.gc();
							}
						}
					}
				}
				if(!gamecmd)
					MessageHandler.respond(event, "That is not a valid game command.");
			}
			else
				MessageHandler.respond(event, "This channel is currently not playing a game.");
		}
	}
}