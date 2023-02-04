package com.github.buddhabotmc.commands;

import com.github.buddhabotmc.utils.Message;
import com.github.buddhabotmc.utils.ResourceManager;

public class AddMaster extends Command {

    public AddMaster() {
        commandName = "addmaster";
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

        ResourceManager.getSaveData().masters.add(arg);

        try {
            ResourceManager.save();
        } catch (
                Exception e) {
            e.printStackTrace();
            Message.sendMessage("Error saving data");
            return;
        }

        Message.sendMessage("New master added.");
    }
}
