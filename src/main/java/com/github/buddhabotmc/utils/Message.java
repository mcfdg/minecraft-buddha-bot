package com.github.buddhabotmc.utils;

import net.minecraft.client.Minecraft;

import java.util.Random;

public class Message {

    static Random random = new Random();

    public static void sendMessage(String message) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(message + " [" + randomChars(16) + "]");
    }

    public static void sendMessage(String message, boolean possiblyDouble) {
        if (possiblyDouble)
            sendMessage(message);
        else
            Minecraft.getMinecraft().thePlayer.sendChatMessage(message);
    }

    static String randomChars(int amount) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            result.append((char) ('!' + random.nextInt(92)));
        }
        return result.toString();
    }
}
