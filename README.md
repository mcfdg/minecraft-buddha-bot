### Buddha Bot

> The intelligent interactive boosting companion

Buddha Bot was made for a niche minecraft game mode called "two player parkour". See [For Users](##for-users) for a guide that explains how to use the bot for this game.

The code has also been written in such a way that new commands can be added very easily to create your own custom bot. See [For Developers](##for-developers) for a setup guide and how to customize the bot.

## For Users

_Guide coming soon_

## For Developers

#### Configuration

In the src folder you will find the `Bot.java` file. These are the variables that you can use to customize your bot:

`BOTACCOUNT`: The username of the minecraft account that runs the bot <br>
`COMMAND`:  The name of the command, for example "!buddha" <br>
`CONTEXT`: Textual description of the situation for the chatbot. This can be anything. <br>
`PATH_LOG`: The path where it saves the log for debugging <br>
`PATH_SAVEDATA`: The path where it saves data (users, recent chat interactions) <br>
`MASTERS`: A list of usernames of people who should be able to use master commands, this includes the bot itself

There is also an environment variable `GOOSEAI_API_KEY` that must be set to your https://goose.ai API key if you want to use the chatbot functionality.
#### Adding new commands

Creating and adding new commands is dirt easy. 

In the `commands` folder you can create a new class that you can give the name of the command. Please look at other commands for as an example.

This class must extend the `Command` class, have an initializer, and must override the `execute` function.

The initializer only has to set the super class variable `commandName` to the name of the command, e.g. "help".

Now you can put any code you want to execute when someone types this command in the execute function. There are some helper functions though:

`isMaster(String username)`: Check if the user who used the command is a master. <br>
`extractArgument(String playerMessage)`: Get the argument of the command if it exists. <br> 
`Message.sendMessage(String message, boolean bypassSpam)`: This sends a message to the chat. But also adds random characters after the message to bypass spam filters in case the bot has to say the same thing twice.

Lastly you must add this command to the `unmappedCommands` list in `Bot.java`. That's it!#### Building and installing

#### Building and installation
_Guide coming soon_