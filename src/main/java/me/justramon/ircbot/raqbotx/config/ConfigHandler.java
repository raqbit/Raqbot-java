package me.justramon.ircbot.raqbotx.config;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import me.justramon.ircbot.raqbotx.core.Core;
import me.justramon.ircbot.raqbotx.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigHandler {
    public static File configfile;
    public static Config config;

    public static void loadConfig() {
        configfile = new File("config.yml");

        if (!configfile.exists()) {
            setupConfig(configfile);
        }

        try {
            YamlReader configreader = new YamlReader(new FileReader(configfile));
            config = configreader.read(Config.class);
        } catch (FileNotFoundException | YamlException e) {
            e.printStackTrace();
        }
        System.gc();
    }

    static void setupConfig(File configfile) {
        try {
            configfile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        config = new Config();

        config.nick = "RaqbotX";
        config.devnick = "QbotXDev";
        config.login = "RaqbotX";
        config.realname = "Quantum is amazing";
        config.serverhostname = "irc.esper.net";

        List<String> channels = new ArrayList<String>();
        channels.add("#Raqbit");
        config.channels = channels;

        config.devchan = "#bl4ckb0tTest";

        List<String> operators = new ArrayList<String>();
        operators.add(StringUtils.genrandstring(10));
        config.operators = operators;

        List<String> xtrafunc = new ArrayList<String>();
        xtrafunc.add("#Raqbit");
        config.xtrafunc = xtrafunc;

        List<String> mojangUpdateChans = new ArrayList<String>();
        mojangUpdateChans.add("#Raqbit");
        config.mojangUpdateChans = mojangUpdateChans;

        config.updateDevChan = config.devchan;

        config.gameChannels = config.xtrafunc;

        save();
    }

    public static String getNick() {
        return Core.dev ? config.devnick : config.nick;
    }

    public static void setNick(String newNick) {
        if (!configfile.exists()) {
            setupConfig(configfile);
        }

        if (!Core.dev)
            config.nick = newNick;
        else
            config.devnick = newNick;

        save();
    }

    public static void save() {
        try {
            YamlWriter writer = new YamlWriter(new FileWriter(configfile));
            writer.write(config);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}