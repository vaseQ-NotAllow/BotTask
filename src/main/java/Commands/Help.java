package Commands;

import Bot.BotCore;

public class Help implements ICommands {
    public String getName() {
        return "/help";
    }

    public String execute(BotCore bot, String[] arg) {
        return bot.getInfo();
    }
}
