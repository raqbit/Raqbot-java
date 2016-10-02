package me.justramon.ircbot.justabotx.commands;

import me.justramon.ircbot.justabotx.config.ConfigHandler;
import me.justramon.ircbot.justabotx.core.Core;
import me.justramon.ircbot.justabotx.core.ICommand;
import me.justramon.ircbot.justabotx.util.MessageHandler;
import me.justramon.ircbot.justabotx.util.StringUtils;
import org.pircbotx.hooks.events.MessageEvent;

public class Operator implements ICommand<MessageEvent> {

    @Override
    public void exe(MessageEvent event, String[] args) throws Exception {
        if (args.length == 0) {
            MessageHandler.respond(event, "Please give a first argument, valid options are [List/Add/Remove]");
            return;
        }

        if (args.length < 2) {
            MessageHandler.respond(event, "Please give a username too!");
            return;
        }

        String cmd = args[0].toLowerCase();
        switch (cmd) {
            case "list":
                MessageHandler.respond(event, "[" + StringUtils.listToString(ConfigHandler.config.operators) + "]");
                break;

            case "add":
                for (int i = 1; i < args.length; i++) {
                    if (args[i].isEmpty())
                        continue;

                    ConfigHandler.config.operators.add(args[i]);
                }

                ConfigHandler.save();
                String addedPeople = StringUtils.arrayToString(StringUtils.trimArgrumentsFromCommand(args));
                MessageHandler.respond(event, "Added [" + addedPeople + "]");
                break;

            case "remove":
                for (int i = 1; i < args.length; i++) {
                    if (args[i].isEmpty())
                        continue;

                    ConfigHandler.config.operators.remove(args[i]);
                }

                ConfigHandler.save();

                String removedPeople = StringUtils.arrayToString(StringUtils.trimArgrumentsFromCommand(args));
                MessageHandler.respond(event, "Removed [" + removedPeople + "]");
                break;

            default:
                MessageHandler.respond(event, "Not a valid first argument, valid options are [List/Add/Remove]");
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"operator"};
    }

    @Override
    public String getInfo() {
        return "List/Add/Remove bot operators";
    }

    @Override
    public String getUsage() {
        return "?<command> <list/add/remove> <nickname> [nickname ...]";
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
