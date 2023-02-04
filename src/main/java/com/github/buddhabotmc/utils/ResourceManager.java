package com.github.buddhabotmc.utils;

import com.github.buddhabotmc.Bot;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceManager {

    static private SaveData saveData = null;

    public static void save() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(Bot.PATH_SAVEDATA)));
        oos.writeObject(getSaveData());
    }

    public static Object load() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(Bot.PATH_SAVEDATA)));
        return ois.readObject();
    }

    /**
     * Returns the instance of the saved data. If it has not been loaded, it will be loaded. If was never saved before
     * a fresh SaveData object is created.
     *
     * @return the saved data
     */
    public static SaveData getSaveData() {
        if (saveData == null) {
            try {
                saveData = (SaveData) load();
            } catch (Exception e) {
                saveData = new SaveData();
            }
        }
        saveData.masters.add("BuddhaBot");  // TODO: super hacky, maybe not necessary
        return saveData;
    }
}
