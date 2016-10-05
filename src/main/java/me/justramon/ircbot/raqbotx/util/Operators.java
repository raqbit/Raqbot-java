package me.justramon.ircbot.raqbotx.util;

import me.justramon.ircbot.raqbotx.config.ConfigHandler;
import me.justramon.ircbot.raqbotx.core.ICommand;
import org.pircbotx.hooks.events.MessageEvent;

public class Operators {
    public static boolean isOp(MessageEvent event) {
        if (event.getUser().isVerified()) {
            for (String s : ConfigHandler.config.operators) {
                if (event.getUser().getNick().equals(s)) {
                    return true;
                }
            }
            event.respond("You are not an operator!");
            return false;
        } else {
            event.respond("Sorry, but you're not verified.");
            return false;
        }
    }

    public static boolean isOpCommand(ICommand<MessageEvent> command) {
        return command.isOperatorCommand();
    }
}
