package tk.justramon.ircbot.justabotx.cmds;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import tk.justramon.ircbot.justabotx.Log;
import tk.justramon.ircbot.justabotx.Ops;

public class Clear
{
	public static void exe(MessageEvent<PircBotX> event, String[] args) throws IOException
	{	
		if(Ops.isOp(event))
		{
			FileUtils.write(Log.getLog(event.getChannel()), "");
			event.respond("Sir, the log has been terminated.");
		}
	}
}
