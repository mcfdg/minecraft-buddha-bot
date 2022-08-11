package com.github.buddhabotmc.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class Open extends Command {

    public Open() {
        commandName = "open";
    }

    @Override
    public void execute(String playerName, String playerMessage) {
        KeyBinding.onTick(Minecraft.getMinecraft().gameSettings.keyBindUseItem.getKeyCode());
    }
}
