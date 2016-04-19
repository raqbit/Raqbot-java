package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.Core;
import me.justramon.ircbot.justabotx.core.ICommand;

public class Enable implements ICommand<MessageEvent>
{

	@Override
	public void exe(MessageEvent event, String[] args) throws Exception
	{
			if(!Core.enabled)
			{
				event.respond("TY m8 for enabling me :D");
				Core.enabled = true;
			}
			else
			{
				event.respond("I am already enabled!!!");
				event.respond("What do you want from me? D:");
			}
		}

	@Override
	public String[] getAliases()
	{
		return new String[] {"enable"};
	}

	@Override
	public String getInfo()
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
