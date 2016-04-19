package me.justramon.ircbot.justabotx.util;

import me.justramon.ircbot.justabotx.core.ConnectionHandler;

public class OtherUtils
{
	public static String getUptime()
	{
		ConnectionHandler.uptime.split();

		String[] uptimeraw = ConnectionHandler.uptime.toSplitString().split("\\.");
		int hours = Integer.parseInt(uptimeraw[0].split(":")[0]);
		int days = (hours / 24) >> 0;

		hours = hours % 24;

		return days + ":" + (hours < 10 ? "0" + hours : hours) + ":" + uptimeraw[0].split(":")[1] + ":" + uptimeraw[0].split(":")[2];
	}
}
