package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.config.ConfigHandler;
import me.justramon.ircbot.justabotx.core.Core;
import me.justramon.ircbot.justabotx.core.ICommand;
import me.justramon.ircbot.justabotx.util.MessageHandler;

public class Nick implements ICommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		if(args.length == 1 && args[0] != null)
		{
			ConfigHandler.setNick(args[1]);
			Core.bot.sendIRC().changeNick(ConfigHandler.config.nick);
		}
		else
			MessageHandler.respond(event, "please give the new nick.");
	}

	@Override
	public String[] getAliases()
	{
		return new String[] {"nick", "setnick"};
	}

	@Override
	public String getInfo()
	{
		return "Set's the bot's nick to the nick given.";
	}
	
	@Override
	public String getUsage()
	{
		return "?<command> <New Nickname>";
	}

	
	@Override
	public boolean xtraFunc()
	{
		return false;
	}
}
