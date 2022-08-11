package com.github.buddhabotmc.tasks;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

import java.util.Date;
import java.util.TimerTask;


public class AttackTask extends TimerTask {

    public AttackTask() {
    }

    public void run() {
        if (Minecraft.getMinecraft().objectMouseOver.entityHit != null) {
            Entity entity = Minecraft.getMinecraft().objectMouseOver.entityHit;
            Minecraft.getMinecraft().playerController.attackEntity(Minecraft.getMinecraft().thePlayer, entity);
        } else {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("Boost failed: There was no player in front of me, please try again");
        }
    }
}
