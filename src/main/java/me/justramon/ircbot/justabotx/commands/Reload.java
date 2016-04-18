package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.config.ConfigHandler;
import me.justramon.ircbot.justabotx.core.Core;
import me.justramon.ircbot.justabotx.core.ICommand;

public class Reload implements ICommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		event.respond("Config Reloaded.");
		ConfigHandler.loadConfig();
		Core.bot.sendIRC().changeNick(!Core.dev ? ConfigHandler.config.nick : ConfigHandler.config.devnick);
	}

	@Override
	public String[] getAliases()
	{
		return new String[] {"reload", "rl"};
	}

	@Override
	public String getInfo()
	{
		return "Reloads" + Colors.BOLD + Colors.UNDERLINE + "some" + Colors.NORMAL + "config options like for example the bot's nick.";
	}

}
