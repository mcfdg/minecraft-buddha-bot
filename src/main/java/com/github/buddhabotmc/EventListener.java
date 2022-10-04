package com.github.buddhabotmc;

import com.github.buddhabotmc.tasks.SendMessage;
import com.github.buddhabotmc.utils.Message;
import com.github.buddhabotmc.utils.ResourceManager;
import com.github.buddhabotmc.utils.SaveData;
import com.github.buddhabotmc.utils.Speak;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.*;
import java.util.logging.Logger;

public class EventListener {

    Logger logger;
    SaveData saveData = null;

    public EventListener(Logger logger) {
        this.logger = logger;
    }

    @SubscribeEvent
    public void onWorldJoin(EntityJoinWorldEvent entityJoinWorldEvent) {
        if (entityJoinWorldEvent.entity instanceof EntityPlayer) {
            saveData = ResourceManager.getSaveData();
        }
    }

    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent evt) throws Exception {

        String message_unformatted = evt.message.getUnformattedText();
        String message = cleanChatMessage(message_unformatted);

        String[] messageInfo = extractPlayerNameAndMessage(message);

        if (messageInfo == null) // i.e. if it is not a player message
            return;

        String playerName = messageInfo[0];
        String playerMessage = messageInfo[1];
        String playerMessageLower = playerMessage.toLowerCase();


        if (playerMessageLower.contains(Bot.COMMAND)) {
            if (playerMessageLower.substring(playerMessageLower.indexOf(Bot.COMMAND)).length() < Bot.COMMAND.length() + 1) {
                Bot.commands.get("help").execute(playerName, playerMessageLower);
                return;
            }

            int subCommandBegin = playerMessageLower.indexOf(Bot.COMMAND) + Bot.COMMAND.length() + 1;
            String subCommand = playerMessageLower.substring(subCommandBegin);
            if (subCommand.contains(" "))
                subCommand = subCommand.substring(0, subCommand.indexOf(" "));

            for (String commandName : Bot.commands.keySet()) {
                if (commandName.equals(subCommand)) {
                    Bot.commands.get(commandName).execute(playerName, playerMessageLower);
                    return;
                }
            }
            return;
        }

        if (playerMessage.indexOf(Bot.BOTACCOUNT) == 0) {
            String arg; // player's message to Bot
            try {
                arg = playerMessage.substring(Bot.BOTACCOUNT.length() + 1);
                assert !arg.equals("") && !arg.equals(" ");
            } catch (Exception e) {
                Message.sendMessage("I couldn't hear you!");
                return;
            }

            String response = Speak.generateResponse(playerName, arg);

            if (response != null) {
                SendMessage sendMessage = new SendMessage(response);
                Timer timer = new Timer();
                timer.schedule(sendMessage, 130 * response.length());
            } else {
                // clear memory
                saveData.chatMemory = "";
                ResourceManager.save();

                Message.sendMessage(playerName + " oops it looks like I broke my head and lost all my memories :(");
            }
        }
    }

    public String cleanChatMessage(String message) {
        int index = message.indexOf('\u00a7');

        while (index >= 0) {
            String substrToRemove = message.substring(index, index + 2);
            message = message.replace(substrToRemove, "");
            index = message.indexOf('\u00a7');
            logger.info(message);
        }

        return message;
    }

    private String[] extractPlayerNameAndMessage(String message) {
        String playerName;
        String playerMessage;

        int indexColon = message.indexOf(':'); // hypixel
        if (indexColon < 0)
            indexColon = message.indexOf('>'); // singleplayer
        int indexClosingBracket = message.lastIndexOf(']');

        if (indexColon < 0) // it is not a player message
            return null;

        playerMessage = message.substring(indexColon + 2);

        if (indexClosingBracket < 0)
            playerName = message.substring(0, indexColon);
        else {
            try { // TODO: not neat
                playerName = message.substring(indexClosingBracket + 2, indexColon);
            } catch (Exception e) {
                return null;
            }
        }

        playerName = playerName.replace("<", ""); // hacky

        return new String[]{playerName, playerMessage};
    }
}
