package com.github.buddhabotmc.commands;

import com.github.buddhabotmc.tasks.AttackTask;
import com.github.buddhabotmc.utils.Message;

import java.util.Timer;

public class Boost extends Command {
    public Boost() {
        commandName = "boost";
    }

    @Override
    public void execute(String playerName, String playerMessage) {
        String arg = extractArgument(playerMessage);

        if (arg.equals("")) {
            Message.sendMessage(playerName + " Missing delay, use !buddha boost [milliseconds]");
            return;
        }

        AttackTask attackTask = new AttackTask();
        Timer timer = new Timer();
        timer.schedule(attackTask, Integer.parseInt(arg));
    }
}
