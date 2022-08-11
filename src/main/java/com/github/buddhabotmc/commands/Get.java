package com.github.buddhabotmc.commands;

import com.github.buddhabotmc.utils.Message;
import com.github.buddhabotmc.utils.ResourceManager;
import net.minecraft.client.Minecraft;

public class Get extends Command {

    public Get() {
        commandName = "get";
    }

    @Override
    public void execute(String playerName, String playerMessage) {
        if (hasPaid(playerName)) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/tp " + playerName);
        } else {
            Message.sendMessage(playerName + " You do not have Bot access, you can buy it for just " + ResourceManager.getSaveData().price + " Gold!");
        }
    }
}
