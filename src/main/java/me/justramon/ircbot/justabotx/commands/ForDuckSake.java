package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.ICommand;
import me.justramon.ircbot.justabotx.util.MessageHandler;

public class ForDuckSake implements ICommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		if(args.length > 1 && args[1] != null)
		{
			MessageHandler.sendChannelMessage(event, "ffs " + args[1] + "!");
		}
		else
			MessageHandler.sendChannelMessage(event, "ffs " + event.getUser().getNick() + " please use this command right!!");
	}

	@Override
	public String[] getAliases()
	{
		return new String[] {"ffs"};
	}

	@Override
	public String getInfo()
	{
		return "You'll figure it out :P";
	}

}
