package me.justramon.ircbot.justabotx.cmd;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.Log;
import me.justramon.ircbot.justabotx.util.Ops;

public class Clear
{
	/**
	 * Clears the log of the channel the executor is in.
	 * 
	 * @param event
	 * @param args
	 * @throws IOException
	 */
	public static void exe(MessageEvent<PircBotX> event, String[] args) throws IOException
	{	
		//Check if the user actually has the permission to clear the log.
		if(Ops.isOp(event))
		{
			FileUtils.write(Log.getLog(event.getChannel()), "");
			event.respond("Sir, the log has been terminated.");
		}
	}
}
