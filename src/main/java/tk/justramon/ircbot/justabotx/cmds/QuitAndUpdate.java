package tk.justramon.ircbot.justabotx.cmds;

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

import tk.justramon.ircbot.justabotx.Core;
import tk.justramon.ircbot.justabotx.util.Ops;

public class QuitAndUpdate
{
	public static void quit(MessageEvent<PircBotX> event) throws IOException, InterruptedException, URISyntaxException
	{
		if(Ops.isOp(event))
		{
			event.respond("bye!");
			Thread.sleep(2000);
			Core.bot.sendIRC().quitServer();
			Thread.sleep(2000);
			System.exit(1);
		}
	}

	public static void update(MessageEvent<PircBotX> event) throws Exception
	{
		if (Ops.isOp(event))
		{
			event.getChannel().send().message("Updating!");

			ReadableByteChannel url = Channels.newChannel(new URL("https://dl.dropboxusercontent.com/s/2bfzzat1s9s6363/JustABotX.jar").openStream());
			FileOutputStream file = new FileOutputStream("JustABotX" + getJarInt(true) + ".jar");
			final List<String> updatecommand = new ArrayList<String>();

			updatecommand.add("screen");
			updatecommand.add("-dmS");
			updatecommand.add("JustABotX");
			updatecommand.add("java");
			updatecommand.add("-jar");
			updatecommand.add("JustABotX" + getJarInt(true) + ".jar");
			file.getChannel().transferFrom(url, 0, Long.MAX_VALUE);
			file.close();
			new ProcessBuilder(updatecommand).start();
			Core.bot.sendIRC().quitServer();
			System.exit(0);
		}
	}

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
	}
}
