package me.justramon.ircbot.justabotx.commands;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.features.blogupdates.JRWUpdates;
import me.justramon.ircbot.justabotx.features.blogupdates.MojangUpdates;
import me.justramon.ircbot.justabotx.util.IDevCommand;
import me.justramon.ircbot.justabotx.util.MessageHandler;

public class ForceShow implements IDevCommand<MessageEvent> {

    @Override
    public void exe(MessageEvent event, String[] args) throws Exception {
        switch (args[0]) {
            case "mojang":
                MojangUpdates.debugForceShow();
                break;
            case "justramon":
                JRWUpdates.debugForceShow();
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
