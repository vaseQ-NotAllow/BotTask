package Commands;

import Bot.BotCore;

public class GetTask implements ICommands {
    @Override
    public String getName() {
        return "/task";
    }

    @Override
    public String execute(BotCore bot, String[] arg) {
        return bot.getCondition();
    }
}
