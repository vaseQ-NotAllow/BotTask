import Bot.*;
import Generator.IGenerator;
import Generator.TaskGenerator;
import Messager.ConsoleMessager;
import Messager.IMessager;

public class EntryPoint {
    public static void main(String[] args){
        IGenerator generator = new TaskGenerator("src/main/resources/Tasks.properties");
        IMessager messager = new ConsoleMessager();

        //BotCore botCore = new BotCore();
        //Bot bot = new Bot(messager, );
        //bot.run(args);
    }
}
