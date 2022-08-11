package com.github.buddhabotmc.utils;

import com.github.buddhabotmc.Bot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Speak {

    public static String generateResponse(String playerName, String message) {
        SaveData saveData = ResourceManager.getSaveData();

        saveData.chatMemory = saveData.chatMemory + " \\n " + playerName + ": " + message;
        saveData.chatMemory = saveData.chatMemory + " \\n Friendly dude:";

        String response = Speak.executePost
                (
                        "https://api.goose.ai/v1/engines/fairseq-6-7b/completions",
                        "{\n" +
                                "  \"prompt\": \"" + Bot.CONTEXT + saveData.chatMemory + "\",\n" +
                                "  \"max_tokens\": 16\n" +
                                "}"
                );

        if (response != null) {
            String responseMessage = extractMessage(response); // the message inside the json object

            saveData.chatMemory = saveData.chatMemory + responseMessage;

            trimChatMemory(saveData);

            try {
                ResourceManager.save();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return playerName + " " + responseMessage;
        } else {
            System.out.println("chatbot broke, printing prompt");

            System.out.println(Bot.CONTEXT + saveData.chatMemory);
            return null;
        }
    }

    static void trimChatMemory(SaveData saveData) {
        while (saveData.chatMemory.length() > 500) {
            saveData.chatMemory = saveData.chatMemory.substring(0, saveData.chatMemory.indexOf("\\n") + 2);
        }
    }

    public static String executePost(String targetURL, String urlParameters) {
        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            connection.setRequestProperty("Authorization", Bot.GOOSEAI_API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    //Example: {"choices":[{"finish_reason":"length","index":0,"logprobs":{"text_offset":[0,1,2,3,4,5,6,7,9,16,19,30,31,36,41,49,56,61,65,67,69,70,73,76,81],"tokens":[" "," "," "," "," "," "," "," F","raction"," of"," greenhouse","-","grown"," wild"," mustard"," plants"," with"," red"," c","ot","y","led","ons"," that"," were"]},"text":"        Fraction of greenhouse-grown wild mustard plants with red cotyledons that were","token_index":0}],"created":1659990606,"id":"02a630ff-712a-4f21-9b79-22c5cacd9b19","model":"gpt-j-6b","object":"text_completion"}
    public static String extractMessage(String response) {
        String result = response.substring(response.indexOf("\"text\":\"") + "\"text\":\"".length());
        result = result.substring(0, result.indexOf("\""));
        if (result.contains("\\n"))
            result = result.substring(0, result.indexOf("\\n"));
        return result;
    }
}
