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
    }

    public void run(){
        String input = messager.read();
        messager.write(bot.parseUserInput(input), bot.getId());
        }
}

