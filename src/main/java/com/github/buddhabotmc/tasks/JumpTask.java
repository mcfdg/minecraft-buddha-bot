package com.github.buddhabotmc.tasks;

import net.minecraft.client.Minecraft;

import java.util.TimerTask;


public class JumpTask extends TimerTask {
    private int jumps;

    public JumpTask(int jumps) {
        this.jumps = jumps;
    }

    public void run() {
        Minecraft.getMinecraft().thePlayer.jump();
        jumps--;
        if (jumps <= 0)
            cancel();
    }
}

