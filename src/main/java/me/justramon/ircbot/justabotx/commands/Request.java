package me.justramon.ircbot.justabotx.commands;

import java.io.IOException;
import java.util.List;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.ICommand;
import me.justramon.ircbot.justabotx.features.Logging;
import me.justramon.ircbot.justabotx.features.RequestBlackListHandler;
import me.justramon.ircbot.justabotx.util.MessageHandler;
import me.justramon.ircbot.justabotx.util.StringUtils;

public class Request implements ICommand<MessageEvent> {

    //TODO: Make it stop :P

    @Override
    public void exe(MessageEvent event, String[] args) throws Exception {
        if (!RequestBlackListHandler.isBlackListed(event)) {
            parsetimeunit(event, args);
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"request", "rq"};
    }

    @Override
    public String getInfo() {
        return "returns you the messages & actions that have been sent between the given moment and now.";
    }

    @Override
    public String getUsage() {
        return "?<command> { M | H | D } <int>";
    }

    @Override
    public boolean xtraFunc() {
        return false;
    }

    /**
     * Parses the timeunit the user wants to request in.
     *
     * @param event
     * @param args
     * @throws IOException
     */
    public static void parsetimeunit(MessageEvent event, String[] args) throws IOException {
        switch (args[0].toLowerCase()) {
            case "m":
                evaluate(args, event, "m");
                break;
            case "h":
                evaluate(args, event, "h");
                break;
            case "d":
                evaluate(args, event, "d");
                break;
            default:
                MessageHandler.respond(event, "That's not a valid argument. Use \"d\" for days, \"h\" for hours or \"m\" for minutes");
                break;
        }
    }

    /**
     * Evaluates how many hours/days etc. of log should get shown
     *
     * @param args  The arguments used for the command
     * @param event The event triggered by the command message
     * @param type  The type of time to look up the log (hours/days etc.)
     */
    public static void evaluate(String[] args, MessageEvent event, String type) throws IOException {
        long currentTimestamp = event.getTimestamp();
        try {
            if (args[1] != null && Integer.parseInt(args[1]) > 1)
                sendLines(event, currentTimestamp, Integer.parseInt(args[1]), getTypeSpan(type));
            else
                sendLines(event, currentTimestamp, 1, getTypeSpan(type));
        } catch (NumberFormatException e) {
            MessageHandler.respond(event, "That's not a valid amount.");
            e.printStackTrace();
        }
    }

    /**
     * Gets all messages within the calculated time and sends them to the user
     *
     * @param event            The event triggered by the command message
     * @param currentTimestamp The current UNIX time
     * @param multiplier       How many hours/days etc. should be looked up
     * @param timeSpan         The type of time (hours/days etc.) in seconds
     */
    private static void sendLines(MessageEvent event, long currentTimestamp, int multiplier, int timeSpan) throws IOException {
        List<String> msglist = Logging.readLogLines(Logging.getLog(event.getChannel().getName()));

        boolean found = false;
        boolean sentSpacer = false;

        for (String line : msglist) {
            if ((currentTimestamp - getTimestamp(line)) / 1000 <= timeSpan * multiplier) //3600 = 1 hour in seconds //divide by 1000 becaue of milliseconds
            {
                if (!sentSpacer)
                    event.getUser().send().message("--------------------" + event.getChannel().getName() + "--------------------");

                event.getUser().send().message(StringUtils.getTimeAndDateStamp(line) + " " + getMessage(line));
                found = true;
                sentSpacer = true;
            }
        }

        if (!found) {
            MessageHandler.respond(event, "There wasn't a message in the specified timeframe.");
            return;
        } else {
            String extraDashes = "";

            for (int i = 0; i < event.getChannel().getName().length(); i++) {
                extraDashes += "-";
            }

            event.getUser().send().message("--------------------" + extraDashes + "--------------------");
        }
        msglist = null;
        System.gc();
    }

    /**
     * Gets the timestamp from a log line
     *
     * @param line The log line
     * @return The timestamp
     */
    private static long getTimestamp(String line) {
        return Long.parseLong(line.split(" ")[0]);
    }

    /**
     * Gets the message from a log line
     *
     * @param line The log line
     * @return The message
     */
    private static String getMessage(String line) {
        String result = "";
        String[] words = line.split(" ");

        for (int i = 1; i < words.length; i++) {
            result += words[i] + " ";
        }

        return result.trim();
    }

    /**
     * Gets the amount of time in seconds for hours/days etc.
     *
     * @param type Hours/days etc.
     * @return The amount of time in seconds
     */
    private static int getTypeSpan(String type) {
        switch (type) {
            case "m":
                return 60;
            case "h":
                return 3600;
            case "d":
                return 86400;
            default:
                return 0;
        }
    }

}
