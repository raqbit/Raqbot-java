package tk.justramon.ircbot.justabotx;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

public class Ops
{
	private static File opfile = new File("ops" + ".txt");
	
	public static boolean isOp(MessageEvent<PircBotX> event) throws IOException
	{
		if(!opfile.exists())
			opfile.createNewFile();
		
		List<String> ops = FileUtils.readLines(opfile);
		for(String line : ops)
		{
			if(line.equals(event.getUser().getNick()) && event.getUser().isVerified())
				return true;
		}
		event.respond("You don't have the permission to do that!");
		return false;
	}
}
