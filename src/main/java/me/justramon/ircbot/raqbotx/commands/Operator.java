package me.justramon.ircbot.raqbotx.commands;

import me.justramon.ircbot.raqbotx.config.ConfigHandler;
import me.justramon.ircbot.raqbotx.core.ICommand;
import me.justramon.ircbot.raqbotx.util.MessageHandler;
import me.justramon.ircbot.raqbotx.util.StringUtils;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

public class Operator implements ICommand<MessageEvent> {

    private void sendpm(User user, String message) {
        user.send().message(message);
    }

    @Override
    public void exe(MessageEvent event, String[] args) throws Exception {
        if (args.length == 0) {
            sendpm(event.getUser(),  "Please give a first argument, valid options are [List/Add/Remove]");
            return;
        }


        String cmd = args[0].toLowerCase();
        switch (cmd) {
            case "list":
                sendpm(event.getUser(), "[" + StringUtils.listToString(ConfigHandler.config.operators) + "]");
                break;

            case "add":
                if (args.length < 2) {
                    sendpm(event.getUser(), "Please give a username too!");
                    return;
                }

                for (int i = 1; i < args.length; i++) {
                    if (args[i].isEmpty())
                        continue;

                    ConfigHandler.config.operators.add(args[i]);
                }

                ConfigHandler.save();
                String addedPeople = StringUtils.arrayToString(StringUtils.trimArgrumentsFromCommand(args));
                sendpm(event.getUser(), "Added [" + addedPeople + "]");
                break;

            case "remove":
                if (args.length < 2) {
                    sendpm(event.getUser(), "Please give a username too!");
                    return;
                }

                for (int i = 1; i < args.length; i++) {
                    if (args[i].isEmpty())
                        continue;

                    ConfigHandler.config.operators.remove(args[i]);
                }

                ConfigHandler.save();

                String removedPeople = StringUtils.arrayToString(StringUtils.trimArgrumentsFromCommand(args));
                sendpm(event.getUser(),  "Removed [" + removedPeople + "]");
                break;

            default:
                sendpm(event.getUser(), "Not a valid first argument, valid options are [List/Add/Remove]");
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
