package com.github.buddhabotmc.utils;

import com.github.buddhabotmc.Bot;
import net.minecraftforge.event.world.WorldEvent;

import java.util.ArrayList;

public class SaveData implements java.io.Serializable {
    public ArrayList<String> paidUsers = new ArrayList<String>();
    public int price = 5;
    public int tokensUsed = 0;
    public String chatMemory;
    public ArrayList<String> masters = new ArrayList<String>();

    public SaveData(){
        masters.add(Bot.BOTACCOUNT);
    }
}
