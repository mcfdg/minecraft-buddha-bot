package com.github.buddhabotmc.commands;

import com.github.buddhabotmc.tasks.JumpTask;
import com.github.buddhabotmc.utils.Message;
import com.github.buddhabotmc.utils.ResourceManager;

import java.util.Timer;

public class Jump extends Command {

    public Jump() {
        commandName = "jump";
    }

    @Override
    public void execute(String playerName, String playerMessage) {
        JumpTask jumpTask = new JumpTask(10);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(jumpTask, 0, 700);
    }
}
