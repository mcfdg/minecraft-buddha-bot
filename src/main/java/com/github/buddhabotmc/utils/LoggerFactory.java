package com.github.buddhabotmc.utils;

import com.github.buddhabotmc.Bot;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerFactory {

    public static Logger createLogger() {
        Logger logger = Logger.getLogger(Bot.BOTACCOUNT + "Bot Log");
        FileHandler fh;
        try {
            fh = new FileHandler(Bot.PATH_LOG);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logger;
    }
}
