package tk.justramon.ircbot.justlogbotx.cmds;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

public class About
{

	public static void exe(MessageEvent<PircBotX> event, String[] args)
	{
		event.respond("JustLogBotX was made by JustRamon with lots of help from bl4ckscor3.");
	}

}
