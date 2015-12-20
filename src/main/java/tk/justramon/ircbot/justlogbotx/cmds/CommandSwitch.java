package tk.justramon.ircbot.justlogbotx.cmds;

import java.io.IOException;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

public class CommandSwitch
{
	public static void exe(MessageEvent<PircBotX> event, String[] args) throws Exception
	{
		switch(args[0].substring(1))
		{
			case "request": Request.exe(event, args); break;
			case "clear": Clear.exe(event, args); break;
			case "about": About.exe(event); break;
			case "help": Help.exe(event);
			case "quit": QuitAndUpdate.quit(event);
			case "update": QuitAndUpdate.update(event);
			default: break;
		}
	}
}
