package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.Core;
import me.justramon.ircbot.justabotx.core.ICommand;
import me.justramon.ircbot.justabotx.util.MessageHandler;

public class Disable implements ICommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
			if(Core.enabled)
			{
				MessageHandler.respond(event, "Why u do dis?");
				MessageHandler.channelAction(event, "walks into the corner.");
				Core.enabled = false;
			}
			else
			{
				event.respond("I am already disabled!!!");
				MessageHandler.channelAction(event, "walks back into his corner");
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
		return "Disables the bot to where he won't listen to commands.";
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
