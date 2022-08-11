package com.github.buddhabotmc.commands;

import com.github.buddhabotmc.utils.Message;
import com.github.buddhabotmc.utils.ResourceManager;

public class UserList extends Command {

    public UserList() {
        commandName = "userlist";
    }

    @Override
    public void execute(String playerName, String playerMessage) {
        Message.sendMessage(ResourceManager.getSaveData().paidUsers.toString());
    }
}
