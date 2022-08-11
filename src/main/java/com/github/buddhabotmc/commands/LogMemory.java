package com.github.buddhabotmc.commands;

import com.github.buddhabotmc.utils.Message;
import com.github.buddhabotmc.utils.ResourceManager;

public class LogMemory extends Command {

    public LogMemory() {
        commandName = "logmemory";
    }

    @Override
    public void execute(String playerName, String playerMessage) {
        Message.sendMessage(Integer.toString(ResourceManager.getSaveData().chatMemory.length()));
    }
}
