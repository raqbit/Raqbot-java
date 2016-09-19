package me.justramon.ircbot.justabotx.features.blogupdates;

public class BlogUpdateHandler {
    public static Runnable timer = new Runnable() {
        public void run() {
            JRWUpdates.run();
            MojangUpdates.run();
        }

    };
}
