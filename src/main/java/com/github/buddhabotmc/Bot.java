package com.github.buddhabotmc;

import com.github.buddhabotmc.commands.*;
import com.github.buddhabotmc.utils.LoggerFactory;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

@Mod(modid = Bot.MODID, version = Bot.VERSION)
public class Bot {

    /**
     * Mod variables for Forge
     */
    public static final String MODID = "mod_BuddhaBot";
    public static final String VERSION = "1.0.0";

    /**
     * Important: Customize bot username
     */
    public final static String BOTACCOUNT = "BuddhaBot";

    /**
     * Customize the command name
     */
    public final static String COMMAND = "!buddha";

    /**
     * Set the context of the bot
     */
    public static final String CONTEXT = "This is a chat between a Friendly dude and various other people. " +
            "The friendly dude is helpful, curious about the other players, and always friendly. \\n";

    /**
     * Set the paths for the log and save data files
     */
    public static final String PATH_LOG = "\\home\\buddhabot\\Documents\\buddha_bot.log";
    public static final String PATH_SAVEDATA = "\\home\\buddhabot\\Documents\\buddha_bot_data";

    /**
     * Define who are the bot masters. These people can use master commands. (It includes the bot itself.)
     */
    public static final String[] MASTERS = new String[]{BOTACCOUNT};

    /**
     * To set the API key, add it to the environment variables with name `GOOSEAI_API_KEY`
     */
    public static String GOOSEAI_API_KEY = "Bearer " + System.getenv().get("GOOSEAI_API_KEY");

    /**
     * Add new command classes here
     */
    public static final ArrayList<Command> unmappedCommands = new ArrayList<Command>() {{
        add(new Boost());
        add(new Add());
        add(new ClearMemory());
        add(new Get());
        add(new Help());
        add(new Jump());
        add(new LogMemory());
        add(new MemorySize());
        add(new Open());
        add(new SetPrice());
        add(new Remove());
        add(new UserList());
    }};

    public static HashMap<String, Command> commands = new HashMap<String, Command>();

    public Bot() {
        for (Command command : unmappedCommands)
            commands.put(command.commandName, command);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Logger logger = LoggerFactory.createLogger();
        MinecraftForge.EVENT_BUS.register(new EventListener(logger));
    }
}
