package me.justramon.ircbot.raqbotx.commands;

import me.justramon.ircbot.raqbotx.core.Core;
import me.justramon.ircbot.raqbotx.core.ICommand;
import me.justramon.ircbot.raqbotx.util.MessageHandler;
import org.pircbotx.hooks.events.MessageEvent;

public class Disable implements ICommand<MessageEvent> {

    @Override
    public void exe(MessageEvent event, String[] args) throws Exception {
        if (Core.enabled) {
            MessageHandler.respond(event, "Disabled.");
            Core.enabled = false;
        } else {
            MessageHandler.respond(event, "I am already disabled.");
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"disable"};
    }

    @Override
    public String getInfo() {
        return "Disables the bot to where he won't listen to commands.";
    }

    @Override
    public boolean xtraFunc() {
        return false;
    }

    @Override
    public boolean isOperatorCommand() {
        return true;
    }

}
