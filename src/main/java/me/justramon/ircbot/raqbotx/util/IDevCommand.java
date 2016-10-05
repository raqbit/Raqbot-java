package me.justramon.ircbot.raqbotx.util;

import org.pircbotx.hooks.Event;

public interface IDevCommand<M extends Event> {
    void exe(M event, String[] args) throws Exception;

    String[] getAliases();
}
