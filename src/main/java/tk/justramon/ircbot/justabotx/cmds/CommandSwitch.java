package tk.justramon.ircbot.justabotx.cmds;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import tk.justramon.ircbot.justabotx.features.MojangUpdates;

public class CommandSwitch
{
	public static void exe(MessageEvent<PircBotX> event, String[] args) throws Exception
	{		
		switch(args[0].substring(1).toLowerCase())
		{
		case "request": Request.exe(event, args); break;
		case "hello": MsgCmds.itsme(event); break;
		case "clear": Clear.exe(event, args); break;
		case "about": MsgCmds.sendAbout(event); break;
		case "help": MsgCmds.sendHelp(event); break;
		case "version": MsgCmds.sendVersion(event); break;
		case "source": MsgCmds.sendSource(event); break;
		case "quit": QuitAndUpdate.quit(event); break;
		case "update": QuitAndUpdate.update(event); break;
		case "forceshowmojangupdate": MojangUpdates.debugForceShow(event); break;
		case "disable": ChangeState.disable(event); break;
		default: break;
		}
	}
}
