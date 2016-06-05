package me.justramon.ircbot.justabotx.core;

import java.util.LinkedList;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.commands.About;
import me.justramon.ircbot.justabotx.commands.Clear;
import me.justramon.ircbot.justabotx.commands.Disable;
import me.justramon.ircbot.justabotx.commands.Enable;
import me.justramon.ircbot.justabotx.commands.ForDuckSake;
import me.justramon.ircbot.justabotx.commands.ForceShow;
import me.justramon.ircbot.justabotx.commands.Help;
import me.justramon.ircbot.justabotx.commands.Nick;
import me.justramon.ircbot.justabotx.commands.Quit;
import me.justramon.ircbot.justabotx.commands.Reload;
import me.justramon.ircbot.justabotx.commands.Request;
import me.justramon.ircbot.justabotx.commands.Source;
import me.justramon.ircbot.justabotx.commands.TestCommand;
import me.justramon.ircbot.justabotx.features.XtraFunc;
import me.justramon.ircbot.justabotx.features.gamemode.GameModeHandler;
import me.justramon.ircbot.justabotx.features.gamemode.IGame;
import me.justramon.ircbot.justabotx.util.IDevCommand;
import me.justramon.ircbot.justabotx.util.MessageHandler;
import me.justramon.ircbot.justabotx.util.Operators;

public class CommandHandler extends ListenerAdapter
{
	public static LinkedList<ICommand<MessageEvent>> commands = new LinkedList<ICommand<MessageEvent>>();
	private LinkedList<IDevCommand<MessageEvent>> devcommands = new LinkedList<IDevCommand<MessageEvent>>();

	public CommandHandler()
	{
		devcommands.add(new TestCommand());
		devcommands.add(new ForceShow());
		commands.add(new Enable());
		commands.add(new Disable());
		commands.add(new Quit());
		commands.add(new Clear());
		commands.add(new Reload());
		commands.add(new Help());
		commands.add(new Request());
		commands.add(new ForDuckSake());
		commands.add(new Nick());
		commands.add(new Source());
		commands.add(new Nick());
		commands.add(new About());
	}

	public void onMessage(MessageEvent event) throws Exception
	{
		String[] args = event.getMessage().split(" ");
		String cmdName = args[0];

		if(cmdName.startsWith("?"))
		{
			for (ICommand<MessageEvent> cmd : commands)
			{
				if (Core.enabled || cmd instanceof Enable || cmd instanceof Disable)
				{
					for (String s : cmd.setAliases())
					{
						if (cmdName.equalsIgnoreCase("?" + s))
						{
							if(XtraFunc.isExtraFuncCommand(cmd))
							{
								if(XtraFunc.hasXtraFuncEnabled(event.getChannel().getName()))
								{
									cmd.exe(event, args);
									System.gc();
									return;
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
										return;
									}
								}
								else
								{
									cmd.exe(event, args);
									System.gc();
									return;
								}
							}
						}
					}
				}
			}

			for(IDevCommand<MessageEvent> cmd : devcommands)
			{
				if(Core.enabled)
				{
					for(String s : cmd.setAliases())
					{
						if (cmdName.equalsIgnoreCase("?" + s))
						{
							if(Core.dev)
							{
								if(Operators.isOp(event))
								{
									cmd.exe(event, args);
									System.gc();
									return;
								}
							}
						}
					}

				}
			}
		}
		else if(cmdName.startsWith("@"))
		{
			String channel = event.getChannel().getName();

			if(cmdName.equalsIgnoreCase("@" + "disable") && Operators.isOp(event))
			{
				if(GameModeHandler.isPlaying(channel))
				{
					GameModeHandler.disableGameMode(channel);
				}
			}

			if(cmdName.equalsIgnoreCase("@" + "enable") && Operators.isOp(event))
			{
				if(!GameModeHandler.isPlaying(channel))
				{
					GameModeHandler.enableGameMode(channel);
				}
				else
					MessageHandler.respond(event, "This channel is already in game mode!");
			}
			else
			{
				if(GameModeHandler.isPlaying(channel))
				{
					for(IGame game : GameModeHandler.games)
					{
						for(String s : game.setCommands())
						{
							if(cmdName.equalsIgnoreCase("@" + s))
							{
								game.exe(event, args);
								System.gc();
								return;
							}
						}
					}
				}
				else
					MessageHandler.respond(event, "This channel is not in game mode!");
			}
		}
	}
}