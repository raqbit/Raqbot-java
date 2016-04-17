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

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.MessageEvent;

public class Logging extends ListenerAdapter<PircBotX>
{

	public void logMessage(MessageEvent<PircBotX> event)
	{
		System.out.println("Logging message to log.");
		File log = new File("logs/" + event.getChannel().getName() + ".jabxlog");
		checkFiles(log);
		
		List<String> loglines = readLines(log);
		
		loglines.add(event.getTimestamp() + " <" + event.getUser().getNick() + "> " + event.getMessage());
		loglines = null;
		System.gc();
	}

	public static void logAction(ActionEvent<PircBotX> event)
	{
		System.out.println("Logging action to log");
		File log = new File("logs/" + event.getChannel().getName() + ".jabxlog");
		checkFiles(log);
		
		List<String> loglines = readLines(log);
		
		loglines.add(event.getTimestamp() + " * " + event.getUser().getNick() + " " + event.getAction());
		loglines = null;
		System.gc();
	}

	static void checkFiles(File log)
	{
		File logfolder = new File("logs");

		if(!logfolder.exists())
			logfolder.mkdirs();


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

	private static List<String> readLines(File log)
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
	
	private static void writeLines(File log, List<String> lines)
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
}
