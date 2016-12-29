package me.justramon.ircbot.raqbotx.core;

import me.justramon.ircbot.raqbotx.commands.*;
import me.justramon.ircbot.raqbotx.features.XtraFunc;
import me.justramon.ircbot.raqbotx.features.gamemode.GameModeHandler;
import me.justramon.ircbot.raqbotx.features.gamemode.IGame;
import me.justramon.ircbot.raqbotx.util.IDevCommand;
import me.justramon.ircbot.raqbotx.util.MessageHandler;
import me.justramon.ircbot.raqbotx.util.Operators;
import me.justramon.ircbot.raqbotx.util.StringUtils;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.LinkedList;

public class CommandHandler extends ListenerAdapter {
    public static LinkedList<ICommand<MessageEvent>> commands = new LinkedList<ICommand<MessageEvent>>();
    private LinkedList<IDevCommand<MessageEvent>> devcommands = new LinkedList<IDevCommand<MessageEvent>>();

    public CommandHandler() {
        commands.add(new Enable());
        commands.add(new Disable());
        commands.add(new Quit());
        commands.add(new Clear());
        commands.add(new Reload());
        commands.add(new Help());
        commands.add(new Request());
        commands.add(new ForDucksSake());
        commands.add(new Nick());
        commands.add(new Source());
        commands.add(new Nick());
        commands.add(new About());
        commands.add(new Operator());
        commands.add(new Eightball());

        if (Core.dev) {
            devcommands.add(new ForceShow());
        }
    }

    public void onMessage(MessageEvent event) throws Exception {
        String[] args = StringUtils.trimArgrumentsFromCommand(event.getMessage().split(" "));
        String cmdName = event.getMessage().split(" ")[0];

        if (cmdName.startsWith("?")) {
            for (ICommand<MessageEvent> cmd : commands) {
                if (Core.enabled || cmd instanceof Enable || cmd instanceof Disable) {
                    for (String s : cmd.getAliases()) {
                        if (cmdName.equalsIgnoreCase("?" + s)) {
                            if (XtraFunc.isExtraFuncCommand(cmd)) {
                                if (XtraFunc.hasXtraFuncEnabled(event.getChannel().getName())) {
                                    cmd.exe(event, args);
                                    break;
                                }
                            } else {
                                if (Operators.isOpCommand(cmd)) {
                                    if (Operators.isOp(event)) {
                                        cmd.exe(event, args);
                                        break;
                                    }
                                } else {
                                    cmd.exe(event, args);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (Core.enabled) {
                for (IDevCommand<MessageEvent> cmd : devcommands) {
                    for (String s : cmd.getAliases()) {
                        if (cmdName.equalsIgnoreCase("?" + s)) {
                            if (Operators.isOp(event)) {
                                cmd.exe(event, args);
                                break;
                            }
                        }
                    }
                }
            }
        } else if (cmdName.startsWith("@") && Core.enabled) {
            String channel = event.getChannel().getName();

            if (cmdName.equalsIgnoreCase("@game")) {
                if (Operators.isOp(event) | event.getChannel().getName().equals("#JustRamon")) {
                    if (!GameModeHandler.isPlaying(channel)) {
                        if (args.length == 1 || args.length == 2) {
                            boolean installed = false;
                            for (IGame game : GameModeHandler.games) {
                                if (args[0].equalsIgnoreCase(game.getName())) {
                                    installed = true;
                                    if (args.length == 2) {
                                        MessageHandler.sendChannelMessage(event, args[1] + ": " + event.getUser().getNick() + " wants to play a game with you!");
                                    }
                                    GameModeHandler.enableGameMode(channel, args[0]);
                                }
                            }
                            if (!installed) {
                                MessageHandler.respond(event, "That is not a valid game.");
                                MessageHandler.sendChannelMessage(event, "Currently installed: " + GameModeHandler.listAllGames());
                            }
                        } else {
                            MessageHandler.respond(event, "Please pass an installed game that you want to play.");
                            MessageHandler.sendChannelMessage(event, "Currently installed: " + GameModeHandler.listAllGames());
                        }
                    } else {
                        MessageHandler.respond(event, "This channel is already playing a game!");
                    }
                }
            } else if (cmdName.equalsIgnoreCase("@restart")) {
                if (GameModeHandler.isPlaying(channel)) {
                    GameModeHandler.getGameByName(GameModeHandler.currentlyPlaying.get(channel)).restart(channel);
                } else {
                    MessageHandler.respond(event, "This channel is currently not playing a game.");
                }
            } else if (cmdName.equalsIgnoreCase("@disable")) {
                if (Operators.isOp(event) | event.getChannel().getName().equals("#Raqbit")) {
                    if (GameModeHandler.isPlaying(channel)) {
                        GameModeHandler.disableGameMode(channel);
                    } else {
                        MessageHandler.respond(event, "This channel is currently not playing a game.");
                    }
                }
            } else if (GameModeHandler.isPlaying(channel)) {
                boolean gamecmd = false;
                for (IGame game : GameModeHandler.games) {
                    for (String s : game.getCommands()) {
                        if (GameModeHandler.currentlyPlaying.get(event.getChannel().getName()).equalsIgnoreCase(game.getName())) {
                            if (cmdName.equalsIgnoreCase("@" + s)) {
                                gamecmd = true;
                                game.exeCommand(cmdName, event, args);
                            }
                        }
                    }
                }
                if (!gamecmd)
                    MessageHandler.respond(event, "That is not a valid game command.");
            }

        }
    }
}