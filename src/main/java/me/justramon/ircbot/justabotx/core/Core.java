package me.justramon.ircbot.justabotx.core;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import me.justramon.ircbot.justabotx.config.ConfigHandler;
import me.justramon.ircbot.justabotx.features.Logging;
import me.justramon.ircbot.justabotx.features.blogupdates.BlogUpdateHandler;
import me.justramon.ircbot.justabotx.features.gamemode.GameModeHandler;

/**
 * Copyright 2016 JustRamon
 * @author Ramon
 *
 */
public class Core
{
	public static PircBotX bot;
	public static String version = "1.10.0.1";
	public static boolean dev = false;
	public static boolean enabled = true;

	public static void main(String[] args) throws IOException, IrcException
	{
		// Loading the configuration file.
		ConfigHandler.loadConfig();
		new GameModeHandler();
		
		Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(BlogUpdateHandler.timer, 1, 1, TimeUnit.MINUTES);
		
		// Creating the bot.
		createBot(args);
	}
	
	public static void createBot(String[] args) throws IOException, IrcException
	{
		// Bot configs
		if(args.length > 0 && args[0].equals("-dev"))
		{
			dev = true;
			
			Configuration devconfig;
			
			devconfig = new Configuration.Builder()
					.setName(args.length > 1 ? args[1] : ConfigHandler.config.devnick)
					.addAutoJoinChannel(args.length > 2 ? args[2] : ConfigHandler.config.devchan)
					.setLogin(ConfigHandler.config.login)
					.setRealName(ConfigHandler.config.realname)
					.setAutoReconnect(true)
					.addServer(ConfigHandler.config.serverhostname)
					.setAutoNickChange(true)
					.setCapEnabled(true)

					//Listeners
					.addListener(new Logging())
					.addListener(new CommandHandler())
					.addListener(new ConnectionHandler())

					.buildConfiguration();
			
			bot = new PircBotX(devconfig);
			
			devconfig = null;
			System.gc();
			
			bot.startBot()/*.addLove(Integer.MAX_VALUE)*/;
		}
		else
		{
			Configuration config;
			
			config = new Configuration.Builder()
					.setName(ConfigHandler.config.nick)
					.setLogin(ConfigHandler.config.login)
					.setRealName(ConfigHandler.config.realname)
					.setAutoReconnect(true)
					.addServer(ConfigHandler.config.serverhostname)
					.setAutoNickChange(true)
					.setCapEnabled(true)

					//Listeners
					.addListener(new Logging())
					.addListener(new CommandHandler())
					.addListener(new ConnectionHandler())

					.buildConfiguration();
			
			bot = new PircBotX(config);
			
			config = null;
			System.gc();
			
			bot.startBot()/*.addLove(Integer.MAX_VALUE)*/;
		}
	}
}
