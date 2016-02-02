package tk.justramon.ircbot.justabotx;

import java.io.File;
import java.io.IOException;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.MessageEvent;

import tk.justramon.ircbot.justabotx.cmds.ChangeState;
import tk.justramon.ircbot.justabotx.cmds.CommandSwitch;
import tk.justramon.ircbot.justabotx.cmds.QuitAndUpdate;
import tk.justramon.ircbot.justabotx.features.XtraFunc;
import tk.justramon.ircbot.justabotx.util.Channels;
import tk.justramon.ircbot.justabotx.util.NotImportant.Passwords;

public class Core extends ListenerAdapter<PircBotX>
{
	public static PircBotX bot;
	public static boolean enabled = true;
	public static boolean wip = false;
	public void onMessage(MessageEvent<PircBotX> event) throws Exception
	{
		String[] args = event.getMessage().split(" ");
		
		if(args[0].substring(1).equals("enable"))
			ChangeState.enable(event);
		
		if(Core.enabled)
		{
			if(args[0].startsWith("?"))
				CommandSwitch.exe(event, args);
			
			if((event.getMessage().toLowerCase().contains("*lennyface*") || event.getMessage().toLowerCase().contains("*lenny face*")) && enabled && XtraFunc.isAllowed(event))
				event.respond("( ͡° ͜ʖ ͡°)");
			
			Log.exe(event, args);
		}
	}
	public void onConnect(ConnectEvent<PircBotX> event) throws IOException
	{
		if(wip)
			event.getBot().sendIRC().joinChannel("#bl4ckb0tTest");
		else
			Channels.joinAll(event.getBot());
	}

	public static void main(String[] args) throws Exception
	{
		File oldJar = new File("JustLogBotX" + QuitAndUpdate.getJarInt(true) + ".jar");
		Thread.sleep(3000);
		oldJar.delete();
		if(args.length > 0 && args[0].equals("-wip"))
		{
			wip = true;
			Configuration<PircBotX> configuration = new Configuration.Builder<PircBotX>()
					.setName(args.length > 1 ? args[1] : "JustABotDev")
					.setLogin("JustLogBotX")
					.setRealName("Just LogBot X.")
					.setAutoReconnect(true)
					.setServerHostname("irc.esper.net")
					.setAutoNickChange(true)
					.setCapEnabled(true)
					.addListener(new Core())
					.buildConfiguration();
					bot = new PircBotX(configuration);
					bot.startBot()/*.addLove(Integer.MAX_VALUE)*/;
		}
		else
		{
			Configuration<PircBotX> configuration = new Configuration.Builder<PircBotX>()
					.setName("JustABotX")
					.setNickservPassword(Passwords.NICKSERV.getPassword())
					.setLogin("JustABotX")
					.setRealName("Just a Bot X.")
					.setAutoReconnect(true)
					.setServerHostname("irc.esper.net")
					.setAutoNickChange(true)
					.setCapEnabled(true)
					.addListener(new Core())
					.buildConfiguration();
					bot = new PircBotX(configuration);
					bot.startBot();
		}
	}
	public static void setState(boolean state)
	{
		if(state)
			enabled = true;
		else
			enabled = false;
	}
}
