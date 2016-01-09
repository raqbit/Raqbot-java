package tk.justramon.ircbot.justabotx.cmds;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import tk.justramon.ircbot.justabotx.Core;

public class CommandSwitch
{
	public static void exe(MessageEvent<PircBotX> event, String[] args) throws Exception
	{
		if(args[0].substring(1).equals("enable"))
			ChangeState.enable(event);
			
		if(Core.enabled)
		{
			switch(args[0].substring(1))
			{
			case "request": Request.exe(event, args); break;
			case "clear": Clear.exe(event, args); break;
			case "about": About.exe(event); break;
			case "help": Help.exe(event); break;
			case "quit": QuitAndUpdate.quit(event); break;
			case "update": QuitAndUpdate.update(event); break;
			case "disable": ChangeState.disable(event); break;
			default: break;
			}
		}
	}
}