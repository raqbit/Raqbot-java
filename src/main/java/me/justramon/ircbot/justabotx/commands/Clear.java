package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.ICommand;
import me.justramon.ircbot.justabotx.features.Logging;

public class Clear implements ICommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event) throws Exception
	{
		event.getChannel().send().message("Threw logfile into igneous extruder.");
		Logging.clearLog(event.getChannel().getName());
	}

	@Override
	public String[] getAliases()
	{
		return new String[]{"clear", "cl"};
	}

	@Override
	public String getInfo()
	{
		return "Clears the log of the channel the command is executed in.";
	}

}
