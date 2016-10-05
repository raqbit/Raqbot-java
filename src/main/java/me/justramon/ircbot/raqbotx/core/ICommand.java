package me.justramon.ircbot.raqbotx.core;

public interface ICommand<MessageEvent> {
    void exe(MessageEvent event, String[] args) throws Exception;

    String[] getAliases();

    default String getUsage() {
        return "?<command>";
    }

    String getInfo();

    default boolean xtraFunc() {
        return true;
    }

    default boolean isOperatorCommand() {
        return false;
    }
}
