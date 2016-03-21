package me.justramon.ircbot.justabotx;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.MessageEvent;

public class Log
{

	/**
	 * Adds the message to the log.
	 * @param event
	 * @param args
	 * @throws IOException
	 */
	public static void logMessage(MessageEvent<PircBotX> event, String[] args) throws IOException
	{
		File log = getLog(event.getChannel());
		checkFiles(log);
		if(args[0].startsWith("?") || event.getUser().getNick().endsWith("esper.net"))
			return;

		List<String> loglist = FileUtils.readLines(log);

		loglist.add(event.getTimestamp() + " <" + event.getUser().getNick() + "> " + event.getMessage());
		FileUtils.writeLines(log, loglist);
		loglist = null;
		System.gc();
	}
	
	/**
	 * Adds the action to the log.
	 * @param event
	 * @throws IOException
	 */
	public static void logAction(ActionEvent<PircBotX> event) throws IOException
	{
		File log = getLog(event.getChannel());
		checkFiles(log);
		
		List<String> loglist = FileUtils.readLines(log);

		loglist.add(event.getTimestamp() + " * " + event.getUser().getNick() + " " + event.getAction());
		FileUtils.writeLines(log, loglist);
		loglist = null;
		System.gc();
	}
	

	/**
	 * @param channel
	 * @return the logfile.
	 */
	public static File getLog(Channel channel)
	{
		return new File("logs/" + channel.getName().substring(1) + ".txt");
	}

	/**
	 * Checks if the log file & folder exist.
	 * @param log
	 * @throws IOException
	 */
	static void checkFiles(File log) throws IOException
	{
		File logfolder = new File("logs");

		if(!logfolder.exists())
			logfolder.mkdirs();


		if(!log.exists())
			log.createNewFile();
	}
}
