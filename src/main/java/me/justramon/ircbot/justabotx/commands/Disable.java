package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.Core;
import me.justramon.ircbot.justabotx.core.ICommand;

public class Disable implements ICommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event) throws Exception
	{
			if(Core.enabled)
			{
				event.respond("Why u do dis?");
				event.getBot().sendIRC().action(event.getChannel().getName(), "walks into the corner.");
				Core.enabled = false;
			}
			else
			{
				event.respond("I am already disabled!!!");
				event.getBot().sendIRC().action(event.getChannel().getName(), "walks back into his corner");
			}
	}

	@Override
	public String[] getAliases()
	{
		return new String[] {"disable"};
	}

	@Override
	public String getInfo()
	{
		return "Disables JABX to where he won't listen to commands.";
	}

}
