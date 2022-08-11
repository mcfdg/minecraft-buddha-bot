package com.github.buddhabotmc.commands;

import com.github.buddhabotmc.utils.Message;
import com.github.buddhabotmc.utils.ResourceManager;

public class Add extends Command {

    public Add() {
        commandName = "add";
    }

    @Override
    public void execute(String playerName, String playerMessage) {
        if (!isMaster(playerName))
            return;

        String arg = extractArgument(playerMessage);

        if (arg.equals("")) {
            Message.sendMessage("Missing username");
            return;
        }

        ResourceManager.getSaveData().paidUsers.add(arg);

        try {
            ResourceManager.save();
        } catch (
                Exception e) {
            e.printStackTrace();
            Message.sendMessage("Error saving data");
            return;
        }

        Message.sendMessage("Congratulations " + arg + ", you are now a Bot user! I will now serve you.");
    }
}
