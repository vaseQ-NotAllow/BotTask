package Commands;

import Bot.BotCore;

public class Exit implements ICommands {
    public String getName() {
        return "/exit";
    }

    public String execute(BotCore bot, String[] arg) {
        return bot.removeCondition(arg[1]);
    }
}
