package Commands;

import Bot.BotCore;

public class ChangeUser implements ICommands {
    public String getName() {
        return "/changeuser";
    }

    public String execute(BotCore bot, String[] arg) {
        return bot.changeCondition(arg[1]);
    }
}
