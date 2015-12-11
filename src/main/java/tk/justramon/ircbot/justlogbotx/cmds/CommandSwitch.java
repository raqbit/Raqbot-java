package tk.justramon.ircbot.justlogbotx.cmds;

import java.io.IOException;

import org.pircbotx.hooks.types.GenericMessageEvent;

import tk.justramon.ircbot.justlogbotx.cmds.request.Request;

public class CommandSwitch
{

	public static void exe(GenericMessageEvent event, String[] args) throws IOException
	{
		switch(args[0].substring(1))
		{
			case "request": Request.exe(event, args); break;
			case "clear": Clear.exe(event, args); break;
			default: break;
		}
	}

}
