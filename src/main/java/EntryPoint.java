import Bot.*;
import Commands.*;
import Generator.IGenerator;
import Generator.TaskGenerator;
import Messager.ConsoleMessager;
import Messager.IMessager;

import java.util.List;

public class EntryPoint {
    public static void main(String[] args){
        IGenerator generator = new TaskGenerator("src/main/resources/Tasks.properties");
        IMessager messager = new ConsoleMessager();
        ICommands[] commands = {new ChangeUser(), new Exit(), new GetTask(), new Help(), new Start()};
        String info = "I am the bot, that can give you some tasks(/task)\n" +
                "But before that you need login yourself (/start your name)\n" +
                "If you want to change user(/changeuser username), and (/exit) to close you profile\n" +
                "(/help) to repeat this message";
        BotCore botCore = new BotCore(commands, generator, info);
        Bot bot = new Bot(messager, botCore);
        while (true){
            bot.run();
        }
    }
}
