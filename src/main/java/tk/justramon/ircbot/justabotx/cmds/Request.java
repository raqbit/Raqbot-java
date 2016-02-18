package tk.justramon.ircbot.justabotx.cmds;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import tk.justramon.ircbot.justabotx.Log;

public class Request
{
	public static void exe(MessageEvent<PircBotX> event, String[] args) throws IOException
	{
		switch(args[1].toLowerCase())
		{
		case "m": evaluate(args, event, "m"); break;
		case "h": evaluate(args, event, "h"); break;
		case "d": evaluate(args, event, "d"); break;
		default: event.respond("That's not a valid argument. Use \"d\" for days, \"h\" for hours or \"m\" for minutes"); break;
		}
	}

	/**
	 * Evaluates how many hours/days etc. of log should get shown
	 * @param args The arguments used for the command
	 * @param event The event triggered by the command message
	 * @param type The type of time to look up the log (hours/days etc.)
	 */
	public static void evaluate(String[] args, MessageEvent<PircBotX> event, String type) throws IOException
	{
		long currentTimestamp = event.getTimestamp();
		try
		{
			if(args[2] != null && Integer.parseInt(args[2]) > 1)
				sendLines(event, currentTimestamp, Integer.parseInt(args[2]), getTypeSpan(type));
			else
				sendLines(event, currentTimestamp, 1, getTypeSpan(type));
		}
		catch(NumberFormatException e)
		{
			event.respond("That's not a valid amount.");
			e.printStackTrace();
		}
	}

	/**
	 * Gets all messages within the calculated time and sends them to the user
	 * @param event The event triggered by the command message
	 * @param currentTimestamp The current UNIX time
	 * @param multiplier How many hours/days etc. should be looked up
	 * @param timeSpan The type of time (hours/days etc.) in seconds
	 */
	private static void sendLines(MessageEvent<PircBotX> event, long currentTimestamp, int multiplier, int timeSpan) throws IOException
	{
		List<String> msglist = FileUtils.readLines(Log.getLog(event.getChannel()));
		boolean found = false;
		boolean sentSpacer = false;

		for(String line : msglist)
		{
			if((currentTimestamp - getTimestamp(line)) / 1000 <= timeSpan * multiplier) //3600 = 1 hour in seconds //divide by 1000 becaue of milliseconds
			{
				if(!sentSpacer)
					event.getUser().send().message("--------------------" + event.getChannel().getName() + "--------------------");

				event.getUser().send().message(new Date(getTimestamp(line)) + " " + getMessage(line));
				found = true;
				sentSpacer = true;
			}
		}

		if(!found)
		{
			event.respond("There wasn't a message in the specified timeframe.");
			return;
		}
		else
		{
			String extraDashes = "";

			for(int i = 0; i < event.getChannel().getName().length(); i++)
			{
				extraDashes += "-";
			}

			event.getUser().send().message("--------------------" + extraDashes + "--------------------");
		}
		msglist = null;
	}

	/**
	 * Gets the timestamp from a log line
	 * @param line The log line
	 * @return The timestamp
	 */
	private static long getTimestamp(String line)
	{
		return Long.parseLong(line.split(" ")[0]);
	}

	/**
	 * Gets the message from a log line
	 * @param line The log line
	 * @return The message
	 */
	private static String getMessage(String line)
	{
		String result = "";
		String[] words = line.split(" ");

		for(int i = 1; i < words.length; i++)
		{
			result += words[i] + " ";
		}

		return result.trim();
	}

	/**
	 * Gets the amount of time in seconds for hours/days etc.
	 * @param type Hours/days etc.
	 * @return The amount of time in seconds
	 */
	private static int getTypeSpan(String type)
	{
		switch(type)
		{
		case "m": return 60;
		case "h": return 3600;
		case "d": return 86400;
		default: return 0;
		}
	}
}
