package me.justramon.ircbot.justabotx.features;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.pircbotx.hooks.events.MessageEvent;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

import me.justramon.ircbot.justabotx.util.StringUtils;

public class RequestBlackListHandler {
    public static boolean isBlackListed(MessageEvent event) {
        for (String user : loadBlackList(event.getChannel().getName())) {
            if (user.equals(event.getUser().getNick()))
                return true;
        }
        return false;
    }

    public static List<String> loadBlackList(String channel) {
        File blacklistfolder = new File("requestblacklists/");

        if (!new File("requestblacklists").exists())
            blacklistfolder.mkdir();

        File blacklistfile = new File("requestblacklists/" + channel + ".yml");

        if (!blacklistfile.exists()) {
            try {
                blacklistfile.createNewFile();
                RequestBlackList blacklist = new RequestBlackList();

                List<String> blacklistlist = new ArrayList<String>();
                blacklistlist.add(StringUtils.genrandstring(10));
                blacklist.blacklist = blacklistlist;

                YamlWriter writer = new YamlWriter(new FileWriter(blacklistfile));
                writer.write(blacklist);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            YamlReader blacklistreader = new YamlReader(new FileReader(blacklistfile));
            List<String> blacklistreaderlist = blacklistreader.read(RequestBlackList.class).blacklist;
            blacklistreader.close();
            return blacklistreaderlist;
        } catch (IOException e) {
            e.printStackTrace();
            System.gc();
            return null;
        }
    }
}
