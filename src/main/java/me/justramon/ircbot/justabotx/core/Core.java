package me.justramon.ircbot.justabotx.core;

import java.io.IOException;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import me.justramon.ircbot.justabotx.config.ConfigHandler;
import me.justramon.ircbot.justabotx.features.Logging;
import me.justramon.ircbot.justabotx.util.NotImportant.Passwords;

public class Core
{
	public static PircBotX bot;
	public static boolean dev = false;
	public static boolean enabled = true;
	//public static StopWatch uptime;

	public static void main(String[] args) throws IOException, IrcException
	{
		ConfigHandler.loadConfig();
		//uptime.start();
		
		//Bot configs
		if(args.length > 0 && args[0].equals("-dev"))
		{
			//Special config for WIP mode, adding love for obvious reasons.
			dev = true;

			Configuration<PircBotX> configuration = new Configuration.Builder<PircBotX>()
					.setName(args.length > 1 ? args[1] : ConfigHandler.config.devnick)
					.addAutoJoinChannel(args.length > 2 ? args[2] : ConfigHandler.config.devchan)
					.setLogin(ConfigHandler.config.login)
					.setRealName(ConfigHandler.config.realname)
					.setAutoReconnect(true)
					.setServerHostname(ConfigHandler.config.serverhostname)
					.setAutoNickChange(true)
					.setCapEnabled(true)

					//Listeners
					.addListener(new Logging())
					.addListener(new CommandHandler())
					.addListener(new ConnectionHandler())

					.buildConfiguration();
			
			bot = new PircBotX(configuration);
			bot.startBot()/*.addLove(Integer.MAX_VALUE)*/;

			configuration = null;
		}
		else
		{
			//Config for normal mode, adding love for obvious reasons.
			Configuration<PircBotX> configuration = new Configuration.Builder<PircBotX>()
					.setName(ConfigHandler.config.nick)
					.setNickservPassword(Passwords.NICKSERV.getPassword())
					.setLogin(ConfigHandler.config.login)
					.setRealName(ConfigHandler.config.realname)
					.setAutoReconnect(true)
					.setServerHostname(ConfigHandler.config.serverhostname)
					.setAutoNickChange(true)
					.setCapEnabled(true)

					//Listeners
					.addListener(new Logging())
					.addListener(new CommandHandler())
					.addListener(new ConnectionHandler())

					.buildConfiguration();

			bot = new PircBotX(configuration);
			bot.startBot()/*.addLove(Integer.MAX_VALUE)*/;

			configuration = null;
		}
		System.gc();
	}
}
