package tk.justramon.ircbot.justlogbotx;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class Log
{
	static File log = new File("log" + ".txt");
	public static void exe(GenericMessageEvent event, String[] args) throws IOException
	{
		if(args[0].equals("?clear"))
			return;
		
			if(!log.exists())
				log.createNewFile();
			
			List<String> msglist = FileUtils.readLines(log);
			msglist.add("[" + event.getTimestamp() + "] (" + Calendar.getInstance().getTime() + ") <" + event.getUser().getNick() + "> " + event.getMessage());
			FileUtils.writeLines(log,msglist);
	}
	
	public static File getLog()
	{
		return log;
	}
}
