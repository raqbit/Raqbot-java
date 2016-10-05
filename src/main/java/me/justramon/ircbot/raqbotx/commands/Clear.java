package me.justramon.ircbot.raqbotx.commands;

import me.justramon.ircbot.raqbotx.core.ICommand;
import me.justramon.ircbot.raqbotx.features.Logging;
import me.justramon.ircbot.raqbotx.util.MessageHandler;
import org.pircbotx.hooks.events.MessageEvent;

public class Clear implements ICommand<MessageEvent> {

    @Override
    public void exe(MessageEvent event, String[] args) throws Exception {
        MessageHandler.sendChannelMessage(event, "Threw logfile into igneous extruder.");
        Logging.clearLog(event.getChannel().getName());
    }

    @Override
    public String[] getAliases() {
        return new String[]{"clear", "cl"};
    }

    @Override
    public String getInfo() {
        return "Clears the log of the channel the command is executed in.";
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
