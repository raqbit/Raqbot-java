package me.justramon.ircbot.raqbotx.util;

/**
 * Time parser class.
 *
 * @author bl4ckscor3
 */
public class TimeParser {
    //----------------CHANGE LONG TO STRING----------------\\

    public String lts(long l, String format) {
        long s = l / 1000;
        long m = s / 60;
        long h = m / 60;
        long d = h / 24;

        s -= 60 * m;
        m -= 60 * h;
        h -= 24 * d;

        return String.format(format, (d < 10 ? "0" + d : d), (h < 10 ? "0" + h : h), (m < 10 ? "0" + m : m), (s < 10 ? "0" + s : s));
    }
}