package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.ICommand;
import me.justramon.ircbot.justabotx.util.MessageHandler;
import me.justramon.ircbot.justabotx.util.StringUtils;

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
