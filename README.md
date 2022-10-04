### Buddha Bot

> The intelligent interactive boosting companion

Buddha Bot was made for a niche minecraft game mode called "two player parkour". See [For Users](##for-users) for a guide that explains how to use the bot for this game.

The code has also been written in such a way that new commands can be added very easily to create your own custom bot. See [For Developers](##for-developers) for a setup guide and how to customize the bot.

## For Users

_Guide coming soon_

## For Developers

### Setup and build

The setup is very easy.

1. First clone the project: `git clone https://github.com/mcfdg/minecraft-buddha-bot.git`
2. Then open the project in IntelliJ. This will automatically build the gradle project and install the necessary libraries.
3. To build the mod .jar file go to the gradle tasks and run `build` as seen in the image below:

![build_jar](https://user-images.githubusercontent.com/37480406/193841328-2791f9a3-e14f-4d18-8b69-c1d89259ab8c.png)

### Installing the mod

1. Now you can find the mod .jar file in the `build/libs` folder. <br>
2. Put this mod in your `.minecraft/mods` folder.
3. Now you must install forge for 1.8.9: https://files.minecraftforge.net/net/minecraftforge/forge/index_1.8.9.html
4. This will generate a new profile in the minecraft launcher. Launch minecraft with this profile.
5. In the minecraft menu click on mods and check if the mod is in the list.

You are now running a minecraft bot!

### Configuration

For the bot to function properly though, you must first configure it.

In the src folder you will find the `Bot.java` file. These are the variables that you can use to customize your bot:

`BOTACCOUNT`: The username of the minecraft account that runs the bot <br>
`COMMAND`:  The name of the command, for example "!buddha" <br>
`CONTEXT`: Textual description of the situation for the chatbot. This can be anything. <br>
`PATH_LOG`: The path where it saves the log for debugging <br>
`PATH_SAVEDATA`: The path where it saves data (users, recent chat interactions) <br>
`MASTERS`: A list of usernames of people who should be able to use master commands, this includes the bot itself

There is also an environment variable `GOOSEAI_API_KEY` that must be set to your https://goose.ai API key if you want to use the chatbot functionality.

### Adding new commands

Creating and adding new commands is dirt easy:

1. In the `commands` folder you can create a new class that you can give the name of the command. It also must extend the `Command` class. Look at the other commands as an example.
2. Give the class an initializer. The initializer only has to set the super class variable `commandName` to the name of the command, e.g. "help".
3. Override the `execute` function.
4. Now you can put any code you want to execute when someone types this command in this function. There are some helper functions though:

    * `isMaster(String username)`: Check if the user who used the command is a master. <br>
    * `extractArgument(String playerMessage)`: Get the argument of the command if it exists. <br> 
    * `Message.sendMessage(String message, boolean bypassSpam)`: This sends a message to the chat. But also adds random characters after the message to bypass spam filters in case the bot has to say the same thing twice.

5. Lastly you must add this command to the `unmappedCommands` list in `Bot.java`. 

That's it, you have created your first command!
