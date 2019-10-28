package Commands;

import Bot.BotCore;

public interface ICommands {
    String getName();
    String execute(BotCore bot, String[] arg);
}
