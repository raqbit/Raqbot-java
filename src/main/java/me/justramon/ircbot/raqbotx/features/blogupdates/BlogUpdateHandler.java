package me.justramon.ircbot.raqbotx.features.blogupdates;

public class BlogUpdateHandler {
    public static Runnable timer = new Runnable() {
        public void run() {
            MojangUpdates.run();
        }

    };
}
