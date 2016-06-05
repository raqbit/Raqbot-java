package me.justramon.ircbot.justabotx.core;

public interface ICommand<MessageEvent>
{
	public void exe(MessageEvent event, String[] args) throws Exception;
	
	public String[] setAliases();
	
	public default String setUsage()
	{
		return "?<command>";
	}
	
	public String setInfo();
	
	public default boolean xtraFunc()
	{
		return true;
	}
	
	public default boolean isOperatorCommand()
	{
		return false;
	}
}
