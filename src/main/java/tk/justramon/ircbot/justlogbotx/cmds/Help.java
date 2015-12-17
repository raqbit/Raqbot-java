package tk.justramon.ircbot.justlogbotx.cmds;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

public class Help
{
	public static void exe(MessageEvent<PircBotX> event, String[] args)
	{
		event.getUser().send().message("--------------------" + "Help" + "--------------------");
		event.getUser().send().message(Colors.BOLD + "?request <d,h,m> <int>" + Colors.NORMAL + " - Sends the user the messages sent in the current channel, in the specified timeframe.");
		event.getUser().send().message(Colors.BOLD + "?clear" + Colors.NORMAL + " - Clears the log of the current channel. (Op only)");
		event.getUser().send().message(Colors.BOLD + "?help" + Colors.NORMAL + " - Sends this help dialog.");
		event.getUser().send().message(Colors.BOLD + "?about" + Colors.NORMAL + " - Sends an info message.");
		event.getUser().send().message("--------------------------------------------");
	}
}
