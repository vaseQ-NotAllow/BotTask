import Bot.*;
import Generator.IGenerator;
import Generator.TaskGenerator;
import Messager.ConsoleMessager;
import Messager.IMessager;

import java.util.List;

public class EntryPoint {
    public static void main(String[] args){
        IGenerator generator = new TaskGenerator("src/main/resources/Tasks.properties");
        IMessager messager = new ConsoleMessager();
        String info = "I am the bot, that can give you some tasks(/task)\n" +
                "You should sand me your message with @name in start of message\n" +
                "(/help) to repeat this message";
        BotCore botCore = new BotCore(generator, info);
        Bot bot = new Bot(messager, botCore);
        while (true){
            bot.run(null);
        }
    }
}
