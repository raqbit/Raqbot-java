package me.justramon.ircbot.justabotx.core;

import java.util.LinkedList;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import me.justramon.ircbot.justabotx.commands.*;
import me.justramon.ircbot.justabotx.features.XtraFunc;
import me.justramon.ircbot.justabotx.util.IDevCommand;
import me.justramon.ircbot.justabotx.util.Operators;
import me.justramon.ircbot.justabotx.util.StringUtils;

public class CommandHandler extends ListenerAdapter
{
	public static LinkedList<ICommand<MessageEvent>> commands = new LinkedList<ICommand<MessageEvent>>();
	private LinkedList<IDevCommand<MessageEvent>> devcommands = new LinkedList<IDevCommand<MessageEvent>>();

	public CommandHandler()
	{
		devcommands.add(new ForceShow());
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