package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.ICommand;
import me.justramon.ircbot.justabotx.features.Logging;
import me.justramon.ircbot.justabotx.util.MessageHandler;

public class Clear implements ICommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
		MessageHandler.sendChannelMessage(event, "Threw logfile into igneous extruder.");
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
	
	@Override
	public boolean xtraFunc()
	{
		return false;
	}
	
	@Override
	public boolean isOperatorCommand()
	{
		return true;
	}

}
