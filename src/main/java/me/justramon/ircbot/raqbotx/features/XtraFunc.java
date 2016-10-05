package me.justramon.ircbot.raqbotx.features;

import me.justramon.ircbot.raqbotx.config.ConfigHandler;
import me.justramon.ircbot.raqbotx.core.ICommand;
import org.pircbotx.hooks.events.MessageEvent;

public class XtraFunc {
    public static boolean hasXtraFuncEnabled(String channelName) {
        for (String channel : ConfigHandler.config.xtrafunc) {
            if (channelName.equals(channel)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExtraFuncCommand(ICommand<MessageEvent> command) {
        return command.xtraFunc();
    }
}
