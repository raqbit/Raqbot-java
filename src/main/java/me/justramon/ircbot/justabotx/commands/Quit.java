package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.Core;
import me.justramon.ircbot.justabotx.core.ICommand;
import me.justramon.ircbot.justabotx.util.MessageHandler;

public class Quit implements ICommand<MessageEvent> {

    @Override
    public void exe(MessageEvent event, String[] args) throws Exception {
        MessageHandler.sendChannelMessage(event, "Bye guys!");
        Core.bot.sendIRC().quitServer();
        System.exit(1);
    }

    @Override
    public String[] getAliases() {
        return new String[]{"stop", "quit", "goaway"};
    }

    @Override
    public String getInfo() {
        return "Stops the bot.";
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
