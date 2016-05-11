package me.justramon.ircbot.justabotx.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

import me.justramon.ircbot.justabotx.core.Core;
import me.justramon.ircbot.justabotx.util.StringUtils;

public class ConfigHandler
{
	public static File configfile;
	public static Config config;

	public static void loadConfig()
	{
		configfile = new File("config.yml");

		if(!configfile.exists())
		{
			setupConfig(configfile);
		}

		try
		{
			YamlReader configreader = new YamlReader(new FileReader(configfile));
			config = configreader.read(Config.class);
		} 
		catch (FileNotFoundException | YamlException e)
		{
			e.printStackTrace();
		}
		System.gc();
	}
	
	static void setupConfig(File configfile)
	{
		try
		{
			configfile.createNewFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		Config config = new Config();
		
		config.nick = "JustABotX";
		config.devnick = "JABXDev";
		config.login = "JustABotX";
		config.realname = "Just a bot!";
		config.serverhostname = "irc.esper.net";
		
		List<String> channels = new ArrayList<String>();
		channels.add("#JustRamon");
		config.channels = channels;
		
		config.devchan = "#bl4ckb0tTest";
		
		List<String> operators = new ArrayList<String>();
		operators.add(StringUtils.genrandstring(10));
		config.operators = operators;
		
		List<String> xtrafunc = new ArrayList<String>();
		xtrafunc.add("#JustRamon");
		config.xtrafunc = xtrafunc;
		
		List<String> mojangUpdateChans = new ArrayList<String>();
		mojangUpdateChans.add("#JustRamon");
		config.mojangUpdateChans = mojangUpdateChans;
		
		List<String> jrwUpdateChans = new ArrayList<String>();
		jrwUpdateChans.add("#JustRamon");
		config.jrwUpdateChans = jrwUpdateChans;
		
		config.updateDevChan = config.devchan;
		
		try
		{
			YamlWriter writer = new YamlWriter(new FileWriter(configfile));
			writer.write(config);
			writer.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void setNick(String newNick)
	{
		if(!configfile.exists())
		{
			setupConfig(configfile);
		}	
		
		if(!Core.dev)
			config.nick = newNick;
		else
			config.devnick = newNick;
		
		try
		{
			YamlWriter writer = new YamlWriter(new FileWriter(configfile));
			writer.write(config);
			writer.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}