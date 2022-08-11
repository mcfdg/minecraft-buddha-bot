package com.github.buddhabotmc.commands;

import com.github.buddhabotmc.utils.Message;
import com.github.buddhabotmc.utils.ResourceManager;

public class ClearMemory extends Command {

    public ClearMemory(){
        commandName="clearmemory";
    }
    @Override
    public void execute(String playerName, String playerMessage) {
        if (!isMaster(playerName)) {
            Message.sendMessage("Hold on " + playerName + ", you are not allowed to do this!");
            return;
        }

        ResourceManager.getSaveData().chatMemory = "";

        try {
            ResourceManager.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
