package me.justramon.ircbot.justabotx.core;

import java.util.LinkedList;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

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
import me.justramon.ircbot.justabotx.util.IDevCommand;
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
									return;
								}
							}
							else
							{
								if(Operators.isOpCommand(cmd) && Operators.isOp(event))
								{
										cmd.exe(event, args);
										System.gc();
										return;
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
					for(String s : cmd.getAliases())
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
	}
}