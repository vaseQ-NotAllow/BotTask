package Bot;

import Generator.IGenerator;
import Messager.IMessager;
import Tasks.ITask;

public class Bot {
    private IMessager messager;
    private BotCore bot;

    public Bot(IMessager messager, BotCore bot){
        this.messager = messager;
        this.bot = bot;
        messager.write(bot.getInfo(), "");
    }

    public void run(String id){
        String input = messager.read();
        messager.write(bot.parseUserInput(input, id), bot.getId());
        }
}

