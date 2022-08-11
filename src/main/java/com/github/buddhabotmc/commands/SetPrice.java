package com.github.buddhabotmc.commands;

import com.github.buddhabotmc.utils.Message;
import com.github.buddhabotmc.utils.ResourceManager;

public class SetPrice extends Command {
    public SetPrice() {
        commandName = "setprice";
    }

    @Override
    public void execute(String playerName, String playerMessage) {
        if (!isMaster(playerName)) {
            return;
        }

        String arg = extractArgument(playerMessage);

        if (arg.equals("")) {
            Message.sendMessage("Missing price");
            return;
        }

        ResourceManager.getSaveData().price = Integer.parseInt(arg);

        try {
            ResourceManager.save();
        } catch (
                Exception e) {
            e.printStackTrace();
            Message.sendMessage("Error saving data");
            return;
        }

        Message.sendMessage("Price set to " + arg + " Gold");
    }
}
