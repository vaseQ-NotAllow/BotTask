public class EntryPoint {
    public static void main(String[] args){
        IGenerator generator = new TaskGenerator("src/main/resources/Tasks.properties");
        IMessager messager = new ConsoleMessager();
        Bot bot = new Bot(messager, generator);
        bot.run(args);
    }
}
