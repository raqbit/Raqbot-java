package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.config.ConfigHandler;
import me.justramon.ircbot.justabotx.core.ICommand;

public class Help implements ICommand<MessageEvent>
{
	private void sndmsg(User user, String msg)
	{
		user.send().message(msg);
	}

	@Override
	public void exe(MessageEvent event) throws Exception
	{
		sndmsg(event.getUser(), "------------" + ConfigHandler.config.nick + "-Help------------");
		
		sndmsg(event.getUser(), "--------------------------------------------------------------");
	}

	@Override
	public String[] getAliases()
	{
		return new String[] {"help"};
	}

	@Override
	public String getInfo()
	{
		return "Shows you this help menu.";
	}

}
