package me.justramon.ircbot.raqbotx.commands;

import me.justramon.ircbot.raqbotx.core.ICommand;
import me.justramon.ircbot.raqbotx.util.MessageHandler;
import me.justramon.ircbot.raqbotx.util.StringUtils;
import org.pircbotx.hooks.events.MessageEvent;

public class ForDucksSake implements ICommand<MessageEvent> {

    @Override
    public void exe(MessageEvent event, String[] args) throws Exception {
        if (args[0] != null) {
            MessageHandler.sendChannelMessage(event, "For fuck's sake " + StringUtils.arrayToString(args) + "!");
        } else
            MessageHandler.sendChannelMessage(event, "For fuck's sake " + event.getUser().getNick() + " please use this command right!!");
    }

    @Override
    public String[] getAliases() {
        return new String[]{"ffs"};
    }

    @Override
    public String getInfo() {
        return "You'll figure it out :P";
    }

}
