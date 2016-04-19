package me.justramon.ircbot.justabotx.util;

/**
 * Time parser class.
 * @author bl4ckscor3
 *
 */
public class TimeParser
{
	//----------------CHANGE STRING TO LONG----------------\\
	
	public long stl(String s)
	{
		if(s.contains("d"))
			return d(s);
		else if(s.contains("h"))
			return h(s);
		else if(s.contains("m"))
			return m(s);
		else if(s.contains("s"))
			return s(s);
		else return 0;
	}

	private long d(String s)
	{
		long value = Long.parseLong(s.split("d")[0]);

		value *= 24 * 60 * 60;

		if(s.contains("h"))
			return value + h(s);
		else if(s.contains("m"))
			return value + m(s);
		else if(s.contains("s"))
			return value + s(s);
		else
			return value;
	}

	private long h(String s)
	{
		long value = 0;

		if(s.contains("d"))
			value = Long.parseLong(s.split("d")[1].split("h")[0]);
		else
			value = Long.parseLong(s.split("h")[0]);

		value *= 60 * 60;

		if(s.contains("m"))
			return value + m(s);
		else if(s.contains("s"))
			return value + s(s);
		else
			return value;
	}

	private long m(String s)
	{
		long value = 0;

		if(s.contains("d"))
		{
			if(s.contains("h"))
				value = Long.parseLong(s.split("d")[1].split("h")[1].split("m")[0]);
			else
				value = Long.parseLong(s.split("d")[1].split("m")[0]);
		}
		else if(s.contains("h"))
			value = Long.parseLong(s.split("h")[1].split("m")[0]);
		else
			value = Long.parseLong(s.split("m")[0]);

		value *= 60;

		if(s.contains("s"))
			return value + s(s);
		else
			return value;
	}

	private long s(String s)
	{
		long value = 0;

		if(s.contains("d"))
		{
			if(s.contains("h"))
			{
				if(s.contains("m"))
					value = Long.parseLong(s.split("d")[1].split("h")[1].split("m")[1].split("s")[0]);
				else
					value = Long.parseLong(s.split("d")[1].split("h")[1].split("s")[0]);
			}
			else
			{
				if(s.contains("m"))
					value = Long.parseLong(s.split("d")[1].split("m")[1].split("s")[0]);
				else
					value = Long.parseLong(s.split("d")[1].split("s")[0]);
			}
		}
		else if(s.contains("h"))
		{
			if(s.contains("m"))
				value = Long.parseLong(s.split("h")[1].split("m")[1].split("s")[0]);
			else
				value = Long.parseLong(s.split("h")[1].split("s")[0]);
		}
		else if(s.contains("m"))
			value = Long.parseLong(s.split("m")[1].split("s")[0]);
		else
			value = Long.parseLong(s.split("s")[0]);
				
		return value;
	}

	//----------------CHANGE LONG TO STRING----------------\\
	
	public String lts(long l, String format)
	{
		long s = l / 1000;
		long m = s / 60;
		long h = m / 60;
		long d = h / 24;

		s -= 60 * m;
		m -= 60 * h;
		h -= 24 * d;
		
		return String.format(format, (d < 10 ? "0" + d : d), (h < 10 ? "0" + h : h), (m < 10 ? "0" + m : m), (s < 10 ? "0" + s : s));
	}
}