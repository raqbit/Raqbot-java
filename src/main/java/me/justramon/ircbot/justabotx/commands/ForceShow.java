package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.features.blogupdates.MojangUpdates;
import me.justramon.ircbot.justabotx.util.IDevCommand;

public class ForceShow implements IDevCommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		switch(args[1])
		{
			case "mojang": MojangUpdates.debugForceShow(); break;
			case "justramon": MojangUpdates.debugForceShow(); break;
			default: event.respond("That's not a valid blog to force-show updates from."); break;
		}
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"forceshow", "fs"};
	}

}
