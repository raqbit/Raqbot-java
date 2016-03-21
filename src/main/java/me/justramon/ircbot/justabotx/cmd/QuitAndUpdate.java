package me.justramon.ircbot.justabotx.cmd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.Core;
import me.justramon.ircbot.justabotx.util.Ops;

public class QuitAndUpdate
{
	/**
	 * Stops the bot completely.
	 * @param event
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws URISyntaxException
	 */
	public static void quit(MessageEvent<PircBotX> event) throws IOException, InterruptedException, URISyntaxException
	{
		//Checks if the user is allowed to stop JABX.
		if(Ops.isOp(event))
		{
			event.respond("bye!");
			Thread.sleep(2000);
			Core.bot.sendIRC().quitServer("I was ordered to quit.");
			Thread.sleep(2000);
			System.exit(1);
		}
	}

	/**
	 * Switches the jar with a newer version.
	 * @param event
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void update(MessageEvent<PircBotX> event) throws MalformedURLException, IOException, URISyntaxException
	{
		//Checks if the user is allowed to update JABX.
		if (Ops.isOp(event))
		{
			if(!Core.wip)
			{
				event.getChannel().send().message("Updating!");

				//Runs the update sequence.
				updateSequence();
			}
			else
			{
				event.respond("You can't update the bot in WIP mode. The system is made for linux with screen installed.");
			}
		}
	}

	/**
	 * Returns the jar number of the next (or current) jar.
	 * @param opposite
	 * @return
	 * @throws URISyntaxException
	 */
	public static int getJarInt(boolean opposite) throws URISyntaxException
	{
		int number = 0;

		if (opposite == true)
		{
			if (new File(QuitAndUpdate.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getName().contains("1"))
			{
				number = 2;
			}

			if (new File(QuitAndUpdate.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getName().contains("2"))
			{
				number = 1;
			}
		}

		if (opposite == false)
		{
			if (new File(QuitAndUpdate.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getName().contains("1"))
			{
				number = 1;
			}

			if (new File(QuitAndUpdate.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getName().contains("2"))
			{
				number = 2;
			}
		}

		return number;
	};

	/**
	 * Runs the update sequence.
	 * @throws URISyntaxException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	static void updateSequence() throws URISyntaxException, MalformedURLException, IOException
	{
		//Gets updated jar.
		ReadableByteChannel url = Channels.newChannel(new URL("https://dl.dropboxusercontent.com/s/2bfzzat1s9s6363/JustABotX.jar").openStream());
		FileOutputStream file = new FileOutputStream("JustABotX" + getJarInt(true) + ".jar");
		final List<String> updatecommand = new ArrayList<String>();

		//Builds a command to launch a new screen with the new jar.
		updatecommand.add("screen");
		updatecommand.add("-dmS");
		updatecommand.add("JustABotX");
		updatecommand.add("java");
		updatecommand.add("-jar");
		updatecommand.add("JustABotX" + getJarInt(true) + ".jar");
		
		//Stops downloading the jar
		file.getChannel().transferFrom(url, 0, Long.MAX_VALUE);
		file.close();
		//Runs the just built command to run the new jar.
		new ProcessBuilder(updatecommand).start();
		
		//Stops the bot.
		Core.bot.sendIRC().quitServer("Updating!");
		System.exit(0);
	}
}
