package tk.justramon.ircbot.justlogbotx.cmds;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.types.GenericMessageEvent;

import tk.justramon.ircbot.justlogbotx.Log;

public class Clear
{

	public static void exe(GenericMessageEvent<PircBotX> event, String[] args) throws IOException
	{
		if(event.getUser().getNick().equals("JustRamon"))
		{
			FileUtils.write(Log.getLog(), "");
			event.respond("Sir, the log has been terminated.");
		}
		else
			event.respond("You are not permitted to execute this command.");
	}
}
