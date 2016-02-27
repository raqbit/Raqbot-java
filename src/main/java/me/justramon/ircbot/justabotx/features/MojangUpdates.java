package me.justramon.ircbot.justabotx.features;

import java.io.IOException;
import java.net.URL;

import org.pircbotx.Colors;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import me.justramon.ircbot.justabotx.Core;

public class MojangUpdates
{
	private static String lastUri = "";
	public static Runnable timer = new Runnable()
	{
		public void run()
		{
			URL feedUrl;
			SyndFeed feed = null;

			try
			{
				feedUrl = new URL("http://mojang.com/feed.xml");
				feed = new SyndFeedInput().build(new XmlReader(feedUrl));
				SyndEntry latestPost = feed.getEntries().get(0);
				if (!latestPost.getUri().equals(lastUri) && !lastUri.equals("") && !latestPost.getUri().equals(""))
				{
					Core.bot.sendIRC().message("#JustRamon", Colors.BOLD + "New Mojang blog post titled: " + Colors.RED + latestPost.getTitle());
					if(latestPost.getTitle().toLowerCase().contains("snapshot"))
					{
						Core.bot.sendIRC().message("#JustRamon", Colors.BOLD + Colors.GREEN + "Minecraft Snapshot! (Ping: JustRamon)");
					}
					Core.bot.sendIRC().message("#JustRamon", Colors.BOLD + "Link: " + Colors.PURPLE + latestPost.getLink());
				}
				lastUri = latestPost.getUri();
			}
			catch (IllegalArgumentException | FeedException | IOException e)
			{
				e.printStackTrace();
			}
		}
	};
	public static void debugForceShow() throws IOException
	{
		URL feedUrl;
		SyndFeed feed = null;

		try
		{
			feedUrl = new URL("http://mojang.com/feed.xml");
			feed = new SyndFeedInput().build(new XmlReader(feedUrl));
			SyndEntry latestPost = feed.getEntries().get(0);
			Core.bot.sendIRC().message("#JustRamon", Colors.BOLD + "New Mojang blog post titled: " + Colors.RED + latestPost.getTitle());
			if(latestPost.getTitle().toLowerCase().contains("snapshot"))
			{
				Core.bot.sendIRC().message("#JustRamon", Colors.BOLD + Colors.GREEN + "Minecraft Snapshot! (Ping: JustRamon)");
			}
			Core.bot.sendIRC().message("#JustRamon", Colors.BOLD + "Link: " + Colors.PURPLE + latestPost.getLink());
		}
		catch (IllegalArgumentException | FeedException | IOException e)
		{
			e.printStackTrace();
		}
	}
}
