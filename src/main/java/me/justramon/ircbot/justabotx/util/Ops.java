package me.justramon.ircbot.justabotx.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

public class Ops
{
	private static File opfile = new File("ops" + ".txt");
	
	/**
	 * Returns if the user that sent the message is Op or not (In the ops file or not)
	 * 
	 * @param event
	 * @return boolean
	 * @throws IOException
	 */
	public static boolean isOp(MessageEvent<PircBotX> event) throws IOException
	{
		//Creating the file if it doesn't exist
		if(!opfile.exists())
			opfile.createNewFile();
		
		//Enumerating through the file to check if one of the lines contains the user's name & is verified.
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
