package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.Core;
import me.justramon.ircbot.justabotx.core.ICommand;
import me.justramon.ircbot.justabotx.util.MessageHandler;

public class Enable implements ICommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
			if(!Core.enabled)
			{
				MessageHandler.respond(event, "TY m8 for enabling me :D");
				Core.enabled = true;
			}
			else
			{
				MessageHandler.respond(event, "I am already enabled!!!");
				MessageHandler.respond(event, "What do you want from me? D:");
			}
		}

	@Override
	public String[] setAliases()
	{
		return new String[] {"enable"};
	}

	@Override
	public String setInfo()
	{
		return "Enables the bot. When executed he will start listening for commands.";
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
