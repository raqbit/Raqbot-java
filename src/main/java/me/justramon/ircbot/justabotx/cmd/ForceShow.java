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
	/**
	 * Debug force-show for mojang & JustRamonWeb Updates
	 * @param event
	 * @param args
	 * @throws IOException
	 */
	public static void debugForceShow(MessageEvent<PircBotX> event, String[] args) throws IOException
	{
		//Checking if the user is Op or not. 
		if(Ops.isOp(event))
		{
			//Checking if the bot is in wip mode or not.
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
				event.respond("You can only use this command as op in debug mode.");
		}
	}
}
