package me.justramon.ircbot.justabotx.cmd;

import java.io.IOException;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.Core;
import me.justramon.ircbot.justabotx.features.JRWUpdates;
import me.justramon.ircbot.justabotx.features.MojangUpdates;
import me.justramon.ircbot.justabotx.util.Ops;

public class ForceShow
{
	public static void debugForceShow(MessageEvent<PircBotX> event, String[] args) throws IOException
	{
		if(Ops.isOp(event))
		{
			if(Core.wip)
			{
				if(args[1].toLowerCase().equals("mojang"))
				{
					MojangUpdates.debugForceShow();
				}
				if(args[1].toLowerCase().equals("justramon") || args[1].toLowerCase().equals("jr"))
				{
					JRWUpdates.debugForceShow(event);
				}
			}
			else
				event.respond("This is a debug command in wip mode.");
		}
	}
}
