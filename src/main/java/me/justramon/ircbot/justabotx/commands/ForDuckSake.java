package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.ICommand;

public class ForDuckSake implements ICommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		if(args.length > 1 && args[1] != null)
		{
			event.getChannel().send().message("ffs " + args[1] + "!");
		}
		else
			event.getChannel().send().message("ffs " + event.getUser().getNick() + " please use this command right!!");
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
