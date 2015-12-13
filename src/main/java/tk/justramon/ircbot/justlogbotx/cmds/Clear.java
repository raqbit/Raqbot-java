package tk.justramon.ircbot.justlogbotx.cmds;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import tk.justramon.ircbot.justlogbotx.Log;

public class Clear
{
	private static File opfile = new File("ops" + ".txt");
	public static void exe(MessageEvent<PircBotX> event, String[] args) throws IOException
	{
		if(!opfile.exists())
			opfile.createNewFile();
		
		if(isOp(event))
		{
			FileUtils.write(Log.getLog(event.getChannel()), "");
			event.respond("Sir, the log has been terminated.");
		}
		else
			event.respond("You are not permitted to execute this command.");
	}
	public static boolean isOp(MessageEvent<PircBotX> event) throws IOException
	{
		List<String> ops = FileUtils.readLines(opfile);
		for(String line : ops)
		{
			if(line.equals(event.getUser().getNick()))
				return true;
		}
		return false;
	}
}
