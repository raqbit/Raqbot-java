package tk.justramon.ircbot.justlogbotx;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class Log
{
	static File log = new File("log" + ".txt");
	static File exeptionsfile = new File("exeptions" + ".txt");
	public static void exe(GenericMessageEvent<PircBotX> event, String[] args) throws IOException
	{
		if(!log.exists())
			log.createNewFile();
		
		if(!exeptionsfile.exists())
			exeptionsfile.createNewFile();
		
		List<String> exeptions = FileUtils.readLines(exeptionsfile);
		if(startsWithAndContains(exeptions, event.getMessage()))
			return;
		
		if(args[0].startsWith("?") || event.getUser().getNick().endsWith("esper.net"))
			return;

		List<String> msglist = FileUtils.readLines(log);
		
		msglist.add(event.getTimestamp() + " <" + event.getUser().getNick() + "> " + event.getMessage());
		FileUtils.writeLines(log,msglist);
	}

	public static File getLog()
	{
		return log;
	}

public static boolean startsWithAndContains(List<String> list, String line)
{
    for(String s : list)
    {
        if(line.startsWith(s))
            return true;
    }

    return false;
}


 

 
}
