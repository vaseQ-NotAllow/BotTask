package Commands;

import Bot.BotCore;

public class Start implements ICommands {
    public String getName() {
        return "/start";
    }

    public String execute(BotCore bot, String[] arg) {
        if(arg.length == 1)
            return "You missing some arguments";
        return bot.addCondition(arg[1]);
    }
}
