package tk.justramon.ircbot.justlogbotx;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import tk.justramon.ircbot.justlogbotx.cmds.CommandSwitch;

public class Core extends ListenerAdapter<PircBotX>
{
	public void onMessage(MessageEvent<PircBotX> event) throws IOException
	{
		String[] args = event.getMessage().split(" ");

		if(args[0].startsWith("?"))
			CommandSwitch.exe(event, args);

		Log.exe(event, args);
	}
	
	public static void main(String[] args) throws Exception
	{
		if(args.length > 0 && args[0].equals("-wip"))
		{
			Configuration<PircBotX> configuration = new Configuration.Builder<PircBotX>()
					.setName(args.length > 1 ? args[1] : "JustLogBotDev")
					.setServerHostname("irc.esper.net")
					.addAutoJoinChannel("#bl4ckb0tTest")
					.addListener(new Core())
					.buildConfiguration();
			PircBotX bot = new PircBotX(configuration);
			bot.startBot();
		}
		else
		{
		//Configure what we want our bot to do
		Configuration<PircBotX> configuration = new Configuration.Builder<PircBotX>()
				.setName("JustLogBotX")
				.setServerHostname("irc.esper.net")
				.addAutoJoinChannel("#JustRamon")
				.addAutoJoinChannel("#bl4ckscor3")
				.addListener(new Core())
				.buildConfiguration();
		PircBotX bot = new PircBotX(configuration);
		bot.startBot();
		}
	}
}
