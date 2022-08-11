package com.github.buddhabotmc.commands;

import com.github.buddhabotmc.utils.Message;
import com.github.buddhabotmc.utils.ResourceManager;

public class Remove extends Command {

    public Remove() {
        commandName = "remove";
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

        ResourceManager.getSaveData().paidUsers.remove(arg);

        try {
            ResourceManager.save();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Message.sendMessage("Removed " + arg);
    }
}
