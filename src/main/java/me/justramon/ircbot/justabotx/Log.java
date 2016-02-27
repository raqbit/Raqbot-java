package me.justramon.ircbot.justabotx;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

public class Log
{
	
	public static void exe(MessageEvent<PircBotX> event, String[] args) throws IOException
	{
		File logfolder = new File("logs");
		
		if(!logfolder.exists())
			logfolder.mkdirs();
		
		File log = getLog(event.getChannel());
		
		if(!log.exists())
			log.createNewFile();

		if(args[0].startsWith("?") || event.getUser().getNick().endsWith("esper.net"))
			return;

		List<String> msglist = FileUtils.readLines(log);

		msglist.add(event.getTimestamp() + " <" + event.getUser().getNick() + "> " + event.getMessage());
		FileUtils.writeLines(log, msglist);
	}

	public static File getLog(Channel channel)
	{
		return new File("logs/" + channel.getName().substring(1) + ".txt");
	}

	/**
	 * Checks wether the line starts with an element from the list
	 * @param list The list
	 * @param line The line
	 * @return True if the line starts with an element from the list, false if not
	 */
}
