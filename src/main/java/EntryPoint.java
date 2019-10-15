public class EntryPoint {
    public static void main(String[] args){
        IGenerator generator = new TaskGenerator();
        IMessager messager = new ConsoleMessager();
        Bot bot = new Bot(messager, generator);
        bot.run(args);
    }
}
