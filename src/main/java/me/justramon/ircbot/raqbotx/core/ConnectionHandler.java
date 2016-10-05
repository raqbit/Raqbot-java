package me.justramon.ircbot.raqbotx.core;

import me.justramon.ircbot.raqbotx.config.ConfigHandler;
import me.justramon.ircbot.raqbotx.features.RequestBlackListHandler;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;

public class ConnectionHandler extends ListenerAdapter {

    public void onConnect(ConnectEvent event) {

        if (!Core.dev) {
            for (String s : ConfigHandler.config.channels) {
                Core.bot.sendIRC().joinChannel(s);
                RequestBlackListHandler.loadBlackList(s);
            }
        }
    }
}
