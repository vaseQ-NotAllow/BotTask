package Commands;

import Bot.BotCore;

public class ChangeUser implements ICommands {
    public String getName() {
        return "/changeuser";
    }

    public String execute(BotCore bot, String[] arg) {
        if(arg.length == 1)
            return  "You missing some arguments";
        return bot.changeCondition(arg[1]);
    }
}
