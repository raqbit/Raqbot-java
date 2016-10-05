package me.justramon.ircbot.raqbotx.commands;

import me.justramon.ircbot.raqbotx.core.ICommand;
import me.justramon.ircbot.raqbotx.util.MessageHandler;
import org.pircbotx.hooks.events.MessageEvent;

public class Source implements ICommand<MessageEvent> {

    @Override
    public void exe(MessageEvent event, String[] args) throws Exception {
        MessageHandler.respond(event, "Find my source here: https://github.com/raqbit/raqbotx");
    }

    @Override
    public String[] getAliases() {
        return new String[]{"source", "github", "src"};
    }

    @Override
    public String getInfo() {
        return "Returns you the github link for the sourcecode of this bot.";
    }

    @Override
    public boolean xtraFunc() {
        return false;
    }

}
