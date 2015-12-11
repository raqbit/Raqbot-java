package tk.justramon.ircbot.justlogbotx.cmds.request;

import java.io.IOException;

import org.pircbotx.hooks.types.GenericMessageEvent;

public class Request
{

	public static void exe(GenericMessageEvent event, String[] args) throws IOException
	{
		switch(args[1].toLowerCase())
		{
		case "h": Hour.exe(args, event); break;
		case "d": Day.exe(args, event); break;
		default: event.respond("That's not a valid argument. Use \"d\" for days or \"h\" for hours"); break;
		}
	}

}
