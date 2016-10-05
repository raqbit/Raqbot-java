package me.justramon.ircbot.raqbotx.commands;

import me.justramon.ircbot.raqbotx.features.blogupdates.MojangUpdates;
import me.justramon.ircbot.raqbotx.util.IDevCommand;
import me.justramon.ircbot.raqbotx.util.MessageHandler;
import org.pircbotx.hooks.events.MessageEvent;

public class ForceShow implements IDevCommand<MessageEvent> {

    @Override
    public void exe(MessageEvent event, String[] args) throws Exception {
        switch (args[0]) {
            case "mojang":
                MojangUpdates.debugForceShow();
                break;
            default:
                MessageHandler.respond(event, "That's not a valid blog to force-show updates from.");
                break;
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"forceshow", "fs"};
    }

}
