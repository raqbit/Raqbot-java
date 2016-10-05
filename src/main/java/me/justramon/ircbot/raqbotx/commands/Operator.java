package me.justramon.ircbot.raqbotx.commands;

import me.justramon.ircbot.raqbotx.config.ConfigHandler;
import me.justramon.ircbot.raqbotx.core.ICommand;
import me.justramon.ircbot.raqbotx.util.StringUtils;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class Operator implements ICommand<MessageEvent> {

    private void sendpm(User user, String message) {
        user.send().message(message);
    }

    @Override
    public void exe(MessageEvent event, String[] args) throws Exception {
        if (args.length == 0) {
            sendpm(event.getUser(), "Please give a first argument, valid options are [List/Add/Remove]");
            return;
        }


        String cmd = args[0].toLowerCase();
        switch (cmd) {
            case "list":
                String operators = StringUtils.joinNiceString(ConfigHandler.config.operators);
                sendpm(event.getUser(), "List of bot operators: " + operators + "");
                break;

            case "add":
                if (args.length < 2) {
                    sendpm(event.getUser(), "Please give a username to add to the bot operator list!");
                    return;
                }

                for (int i = 1; i < args.length; i++) {
                    if (args[i].isEmpty())
                        continue;

                    ConfigHandler.config.operators.add(args[i]);
                }

                ConfigHandler.save();
                String addedPeople = StringUtils.joinNiceString(Arrays.asList(StringUtils.trimArgrumentsFromCommand(args)));
                sendpm(event.getUser(), "Added " + addedPeople + " to the operator's list");
                break;

            case "remove":
            case "rm":
                if (args.length < 2) {
                    sendpm(event.getUser(), "Please give a username to add to the bot operator list!");
                    return;
                }

                ArrayList<String> actuallyRemoved = new ArrayList<>();
                ArrayList<String> notRemoved = new ArrayList<>();
                for (int i = 1; i < args.length; i++) {
                    if (args[i].isEmpty())
                        continue;

                    // Because we can attempt to remove people not on the list
                    if (ConfigHandler.config.operators.contains(args[i])) {
                        actuallyRemoved.add(args[i]);
                    }
                    else {
                        notRemoved.add(args[i]);
                    }

                    // It'll just ignore it anyway if it isn't in the list
                    ConfigHandler.config.operators.remove(args[i]);
                }

                ConfigHandler.save();

                String removedPeople = StringUtils.joinNiceString(actuallyRemoved);
                sendpm(event.getUser(),  "Removed " + removedPeople + " from the bot operator list");

                if (notRemoved.size() != 0) {
                    String notRemovedPeople = StringUtils.joinNiceString(notRemoved);
                    sendpm(event.getUser(), "The following people were not in the operators list: " + notRemovedPeople);
                }
                break;

            default:
                sendpm(event.getUser(), "Not a valid first argument, valid options are [List/Add/Remove]");
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"operator", "op"};
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
