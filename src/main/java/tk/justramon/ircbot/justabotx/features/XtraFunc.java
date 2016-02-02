package tk.justramon.ircbot.justabotx.features;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

public class XtraFunc
{
	public static boolean isAllowed(MessageEvent<PircBotX> event) throws IOException
	{
		File xtrafile = new File("XtraFunc.txt");
		
		if(!xtrafile.exists())
			xtrafile.createNewFile();
		
		List<String> xtralist = FileUtils.readLines(xtrafile);
		for(String line : xtralist)
		{
			if(line.equals(event.getChannel().getName()))
				return true;
		}
		return false;		
	}
}
