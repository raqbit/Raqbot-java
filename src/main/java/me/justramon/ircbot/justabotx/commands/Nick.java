package me.justramon.ircbot.justabotx.commands;

import me.justramon.ircbot.justabotx.core.CommandHandler;
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
		if (args.length == 2 && args[1].length() > 0) {
            if (args[0].equalsIgnoreCase("dev") || args[0].equalsIgnoreCase("wip")) {
                ConfigHandler.config.devnick = args[1].trim();
            }
			else if (args[0].equalsIgnoreCase("def") || args[0].equalsIgnoreCase("default")) {
                ConfigHandler.config.nick = args[1].trim();
            }

			ConfigHandler.save();
            Core.bot.sendIRC().changeNick(ConfigHandler.getNick());
		}
		else if(args.length == 1 && args[0] != null)
		{
			ConfigHandler.setNick(args[0].trim());
            Core.bot.sendIRC().changeNick(ConfigHandler.getNick());
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
		return "?<command> [dev(devnick)/def(default nick)] <New Nickname>";
	}

	
	@Override
	public boolean xtraFunc()
	{
		return false;
	}
}
