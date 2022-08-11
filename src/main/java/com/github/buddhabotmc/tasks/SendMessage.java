package com.github.buddhabotmc.tasks;

import com.github.buddhabotmc.utils.Message;

import java.util.TimerTask;


public class SendMessage extends TimerTask {
    private String messageToSend;

    public SendMessage(String messageToSend) {
        this.messageToSend = messageToSend;
    }

    public void run() {
        Message.sendMessage(messageToSend, false);
    }
}

