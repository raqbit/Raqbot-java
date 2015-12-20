package tk.justramon.ircbot.justlogbotx.cmds;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import tk.justramon.ircbot.justlogbotx.Ops;
import util.SystemCommandExecutor;

public class QuitAndUpdate
{

	public static void quit(MessageEvent<PircBotX> event) throws IOException, InterruptedException
	{
		if(Ops.isOp(event))
		{
			event.respond("I was ordered to stop by " + event.getUser().getNick() + ".");
			List<String> stopcmd = new ArrayList<String>();
			stopcmd.add("./stop-logbot");

			SystemCommandExecutor stopcommandExecutor = new SystemCommandExecutor(stopcmd);
			stopcommandExecutor.executeCommand();
		}
	}

	public static void update(MessageEvent<PircBotX> event) throws Exception
	{
		if (Ops.isOp(event))
		{
			event.getChannel().send().message("Updating!");

			ReadableByteChannel url = Channels.newChannel(new URL("http://dl.dropboxusercontent.com/s/06ebwijjqhv3rit/JustLogBotX.jar").openStream());
			FileOutputStream file = new FileOutputStream("JustLogBotX" + getJarInt(true) + ".jar");
			final ArrayList<String> updatecommand = new ArrayList<String>();
			updatecommand.add("screen");
			updatecommand.add("-dmS");
			updatecommand.add("JustLogBotX" + getJarInt(true));
			updatecommand.add("java");
			updatecommand.add("-jar");
			updatecommand.add("JustLogBotX" + getJarInt(true) + ".jar");
			file.getChannel().transferFrom(url, 0, Long.MAX_VALUE);
			file.close();
			new ProcessBuilder(updatecommand).start();
			quit(event);
		}
	}

	public static int getJarInt(Boolean opposite) throws URISyntaxException
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
	}
}
