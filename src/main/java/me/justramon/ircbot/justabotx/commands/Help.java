package me.justramon.ircbot.justabotx.commands;

import java.util.Arrays;

import org.pircbotx.Colors;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.config.ConfigHandler;
import me.justramon.ircbot.justabotx.core.CommandHandler;
import me.justramon.ircbot.justabotx.core.ICommand;
import me.justramon.ircbot.justabotx.util.Operators;

public class Help implements ICommand<MessageEvent>
{
	private void sndmsg(User user, String msg)
	{
		user.send().message(msg);
	}

	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		sndmsg(event.getUser(), "------------" + ConfigHandler.config.nick + "-Help------------");

		for(ICommand<MessageEvent> cmd : CommandHandler.commands)
		{
			String aliases = Arrays.toString(cmd.setAliases());
			aliases = aliases.substring(1, aliases.length()-1);
			if(Operators.isOpCommand(cmd))
			{
				sndmsg(event.getUser(), cmd.setUsage().replace("<command>", aliases) + " - " + cmd.setInfo() + " - " + Colors.BOLD + "[OP]" );
			}
			else
			{
				sndmsg(event.getUser(), cmd.setUsage().replace("<command>", aliases) + " - " + cmd.setInfo());
			}
		}
		sndmsg(event.getUser(), "--------------------------------------");
	}

	@Override
	public String[] setAliases()
	{
		return new String[] {"help"};
	}

	@Override
	public String setInfo()
	{
		return "Shows you this help menu.";
	}

	@Override
	public boolean xtraFunc()
	{
		return false;
	}

}
