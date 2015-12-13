package tk.justramon.ircbot.justlogbotx;

import java.io.IOException;

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
		//Configure what we want our bot to do
		Configuration<PircBotX> configuration = new Configuration.Builder<PircBotX>()
				.setName("JustLogBotX")
				.setServerHostname("irc.esper.net")
				.addAutoJoinChannel("#JustRamon")
				.addAutoJoinChannel("#bl4ckb0tTest")
				.addListener(new Core())
				.buildConfiguration();

		//Create our bot with the configuration
		PircBotX bot = new PircBotX(configuration);
		//Connect to the server
		bot.startBot();
	}
}
