package me.justramon.ircbot.justabotx.core;

public interface ICommand<MessageEvent> {
    public void exe(MessageEvent event, String[] args) throws Exception;

    public String[] getAliases();

    public default String getUsage() {
        return "?<command>";
    }

    public String getInfo();

    public default boolean xtraFunc() {
        return true;
    }

    public default boolean isOperatorCommand() {
        return false;
    }
}
