package me.justramon.ircbot.justabotx.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.TimeZone;

public class StringUtils {
    public static String arrayToString(String[] args) {
        String string = "";

        for (int i = 0; i < args.length; i++) {
            string += args[i] + ((i != (args.length - 1)) ? " " : "");
        }

        return string;
    }

    public static String[] trimArgrumentsFromCommand(String[] args) {
        String[] newArgs = new String[args.length - 1];

        for (int i = 0; i < newArgs.length; i++) {
            newArgs[i] = args[i + 1];
        }
        return newArgs;
    }

    public static String getTimeAndDateStamp(String line) {
        Date unixTimeStamp = new Date(Long.parseLong(line.split(" ")[0]));
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy hh:mm:ss a z");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(unixTimeStamp);
    }

    public static String genrandstring(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (new Random().nextFloat() * (rightLimit - leftLimit));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }
}
