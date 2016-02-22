package tk.justramon.ircbot.justabotx.features;

import java.io.IOException;
import java.net.URL;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import tk.justramon.ircbot.justabotx.Core;
import tk.justramon.ircbot.justabotx.util.Ops;

public class JRWUpdates
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
				feedUrl = new URL("http://justramon.me/feed.xml");
				feed = new SyndFeedInput().build(new XmlReader(feedUrl));
				SyndEntry latestPost = feed.getEntries().get(0);
				if (!latestPost.getUri().equals(lastUri) && !lastUri.equals("") && !latestPost.getUri().equals(""))
				{
					Core.bot.sendIRC().message("#JustRamon",  Colors.BOLD + "New" + Colors.GREEN + Colors.BOLD + " JustRamon.me " + Colors.BOLD + "Blogpost called:" + Colors.GREEN + latestPost.getTitle());
					Core.bot.sendIRC().message("#JustRamon",  Colors.BOLD + "Link:" + Colors.PURPLE + latestPost.getLink());
				}
				lastUri = latestPost.getUri();
			}
			catch (IllegalArgumentException | FeedException | IOException e)
			{
				e.printStackTrace();
			}
		}
	};

	public static void debugForceShow(MessageEvent<PircBotX> event) throws IOException
	{
		if(Core.wip && Ops.isOp(event))
		{
			URL feedUrl;
			SyndFeed feed = null;

			try
			{
				feedUrl = new URL("http://justramon.me/feed.xml");
				feed = new SyndFeedInput().build(new XmlReader(feedUrl));
				SyndEntry latestPost = feed.getEntries().get(0);
				if (!latestPost.getUri().equals(lastUri) && !lastUri.equals("") && !latestPost.getUri().equals(""))
				{
					Core.bot.sendIRC().message("#JustRamon",  Colors.BOLD + "New" + Colors.GREEN + Colors.BOLD + " JustRamon.me " + Colors.BOLD + "Blogpost called:" + Colors.GREEN + latestPost.getTitle());
					Core.bot.sendIRC().message("#JustRamon",  Colors.BOLD + "Link:" + Colors.PURPLE + latestPost.getLink());
				}
				lastUri = latestPost.getUri();
			}
			catch (IllegalArgumentException | FeedException | IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			event.respond("This is a debug command and can only be used by ops in wip mode.");
		}
	}
}
