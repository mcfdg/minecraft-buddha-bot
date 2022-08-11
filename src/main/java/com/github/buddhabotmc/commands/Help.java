package com.github.buddhabotmc.commands;

import com.github.buddhabotmc.utils.Message;
import com.github.buddhabotmc.utils.ResourceManager;

public class Help extends Command {

    public Help() {
        commandName = "help";
    }

    @Override
    public void execute(String playerName, String playerMessage) {
        Message.sendMessage("Commands: !buddha <get|open|jump|boost|userlist>");
    }
}
