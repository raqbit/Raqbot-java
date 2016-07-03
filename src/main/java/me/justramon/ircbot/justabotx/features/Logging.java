package me.justramon.ircbot.justabotx.features;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.config.ConfigHandler;
import me.justramon.ircbot.justabotx.core.Core;

public class Logging extends ListenerAdapter
{

	public void onMessage(MessageEvent event)
	{
		File log = new File("logs/" + event.getChannel().getName() + ".jabxlog");
		checkFiles(log);
		
		List<String> loglines = readLogLines(log);
		
		loglines.add(event.getTimestamp() + " <" + event.getUser().getNick() + "> " + event.getMessage());
		
		writeLogLines(log, loglines);
		loglines = null;
		System.gc();
	}
	
	public static void logMyRespondMessage(MessageEvent event, String message)
	{
		File log = new File("logs/" + event.getChannel().getName() + ".jabxlog");
		checkFiles(log);
		
		List<String> loglines = readLogLines(log);
		
		loglines.add(System.currentTimeMillis() + " <" + (!Core.dev ? ConfigHandler.config.nick : ConfigHandler.config.devnick) + "> " +  event.getUser().getNick() + ", " + message);
		
		writeLogLines(log, loglines);
		loglines = null;
		System.gc();
	}
	
	
	
	public static void logMyChannelMessage(MessageEvent event, String message)
	{
		File log = new File("logs/" + event.getChannel().getName() + ".jabxlog");
		checkFiles(log);
		
		List<String> loglines = readLogLines(log);
		
		loglines.add(System.currentTimeMillis() + " <" + (!Core.dev ? ConfigHandler.config.nick : ConfigHandler.config.devnick) + "> " + message);
		
		writeLogLines(log, loglines);
		loglines = null;
		System.gc();
	}
	
	public static void logMyChannelMessage(String channel, String message)
	{
		File log = new File("logs/" + channel + ".jabxlog");
		checkFiles(log);
		
		List<String> loglines = readLogLines(log);
		
		loglines.add(System.currentTimeMillis() + " <" + (!Core.dev ? ConfigHandler.config.nick : ConfigHandler.config.devnick) + "> " + message);
		
		writeLogLines(log, loglines);
		loglines = null;
		System.gc();
	}
	
	public static void logMyAction(MessageEvent event, String action)
	{
		File log = new File("logs/" + event.getChannel().getName() + ".jabxlog");
		checkFiles(log);
		
		List<String> loglines = readLogLines(log);
		
		loglines.add(System.currentTimeMillis() + " * " + (!Core.dev ? ConfigHandler.config.nick : ConfigHandler.config.devnick) + " " + action);
		
		writeLogLines(log, loglines);
		loglines = null;
		System.gc();
	}

	public void onAction(ActionEvent event)
	{
		File log = new File("logs/" + event.getChannel().getName() + ".jabxlog");
		checkFiles(log);
		
		List<String> loglines = readLogLines(log);
		
		loglines.add(event.getTimestamp() + " * " + event.getUser().getNick() + " " + event.getAction());
		writeLogLines(log, loglines);
		loglines = null;
		System.gc();
	}

	static void checkFiles(File log)
	{
		File logfolder = new File("logs");

		if(!logfolder.exists())
			logfolder.mkdir();


		if(!log.exists())
		{
			try
			{
				log.createNewFile();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static List<String> readLogLines(File log)
	{
		try
		{
			FileInputStream finstream = new FileInputStream(log);
			BufferedReader br = new BufferedReader(new InputStreamReader(finstream));

			List<String> lines = new ArrayList<String>();
			String strLine;

			while ((strLine = br.readLine()) != null)
			{
				lines.add(strLine);
			}

			br.close();
			return lines;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}

	}
	
	private static void writeLogLines(File log, List<String> lines)
	{
		try
		{
			FileOutputStream foutstream = new FileOutputStream(log);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(foutstream));
			
			for(String line : lines)
			{
				bw.write(line);
				bw.newLine();
			}
			
			bw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void clearLog(String channelname)
	{
		File log = new File("logs/" + channelname + ".jabxlog");
		
		List<String> loglines = new ArrayList<String>();
		writeLogLines(log, loglines);
	}
	
	public static File getLog(String channelname)
	{
		return new File("logs/" + channelname + ".jabxlog");
	}
}
