package me.justramon.ircbot.raqbotx.commands;

import me.justramon.ircbot.raqbotx.core.ICommand;
import me.justramon.ircbot.raqbotx.util.MessageHandler;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;
import java.util.Random;

public class Eightball implements ICommand<MessageEvent> {

    private String[] answers = new String[] {
            "It is certain",
            "It is decidedly so",
            "Without a doubt",
            "Yes, definitely",
            "You may rely on it",
            "As I see it, yes",
            "Most likely",
            "Outlook good",
            "Yes",
            "Signs point to yes",
            "Reply hazy try again",
            "Ask again later",
            "Better not tell you now",
            "Cannot predict now",
            "Concentrate and ask again",
            "Don't count on it",
            "My reply is no",
            "My sources say no",
            "Outlook not so good",
            "Very doubtful"
    };

    private Random rng = new Random();

    @Override
    public void exe(MessageEvent event, String[] args) throws Exception {
        if (!args[args.length-1].contains("?")) {
            MessageHandler.respond(event, "You must ask me a question!");
            return;
        }

        MessageHandler.respond(event, answers[rng.nextInt(answers.length)]);
    }

    @Override
    public String[] getAliases() {
        return new String[]{"8ball", "eightball"};
    }

    @Override
    public String getInfo() {
        return "Get your very own predictions or questions answered using the magic 8-ball";
    }

    @Override
    public boolean xtraFunc() {
        return true;
    }

}
