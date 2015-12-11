package tk.justramon.ircbot.justlogbotx.cmds.request;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.pircbotx.hooks.types.GenericMessageEvent;

import tk.justramon.ircbot.justlogbotx.Log;

public class Hour
{

	public static void exe(String[] args, GenericMessageEvent event) throws IOException
	{
		if(!args[2].equals(null))
		{
			List<String> msglist = FileUtils.readLines(Log.getLog());
			for(int i = 0; i < msglist.size(); i++)
			{
				if(msglist.get(i).contains(s))
				{
					
				}
			}
		}
	}

}
