package Commands;

import Bot.BotCore;

public class Start implements ICommands {
    public String getName() {
        return "/start";
    }

    public String execute(BotCore bot, String[] arg) {
        return bot.addCondition(arg[1]);
    }
}
