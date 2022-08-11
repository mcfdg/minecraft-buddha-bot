package com.github.buddhabotmc.commands;

import com.github.buddhabotmc.Bot;
import com.github.buddhabotmc.utils.Message;
import com.github.buddhabotmc.utils.ResourceManager;

public abstract class Command {
    public String commandName;

    public abstract void execute(String playerName, String playerMessage);

    public String extractArgument(String playerMessage) {
        if (commandName == null || commandName.equals("")) {
            return "";
        }

        String fullCommand = Bot.COMMAND + " " + commandName;
        String arg;
        try {
            arg = playerMessage.substring(playerMessage.indexOf(fullCommand) + fullCommand.length() + 1);
            if (arg.contains(" ")) {
                arg = arg.substring(0, arg.indexOf(" "));
            }
        } catch (Exception e) {
            arg = "";
        }

        return arg;
    }

    boolean isMaster(String playerName) {
        boolean isMaster = false;
        for (String user : Bot.MASTERS) {
            if (user.equals(playerName)) {
                isMaster = true;
                break;
            }
        }
        if (!isMaster) {
            Message.sendMessage("Hold on " + playerName + ", you are not allowed to do this!");
        }
        return isMaster;
    }

    boolean hasPaid(String playerName) {
        boolean hasPaid = false;
        for (String user : ResourceManager.getSaveData().paidUsers) {
            if (user.equals(playerName)) {
                hasPaid = true;
                break;
            }
        }
        return hasPaid;
    }
}
