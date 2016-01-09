package tk.justramon.ircbot.justabotx.cmds;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import tk.justramon.ircbot.justabotx.Core;

public class About
{
	public static void exe(MessageEvent<PircBotX> event)
	{
		if(Core.wip)
		{
			event.respond("Working!");
		}
		else
		{
		event.respond("JustABotX was made by JustRamon with lots of help from bl4ckscor3.");
		}
	}
}
