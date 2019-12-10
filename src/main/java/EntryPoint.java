import Bot.*;
import Generator.IGenerator;
import Generator.TaskGenerator;
import Messager.ConsoleMessager;
import Messager.IMessager;
import Messager.TgBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class EntryPoint {
    public static void main(String[] args){
        IGenerator generator = new TaskGenerator("src/main/resources/Tasks.properties");
        IMessager messager = TelegramBuild();
        String info = "I am the bot, that can give you some tasks(/task)\n" +
                "You should sand me your message with @name in start of message(Not for telegram)\n" +
                "(/help) to repeat this message";
        BotCore botCore = new BotCore(generator, info);
        Bot bot = new Bot(messager, botCore);
        while (true)
            bot.run();
    }

    private static IMessager ConsoleBot(){
        return new ConsoleMessager();
    }

    private static IMessager TelegramBuild(){
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        TgBot tgBot = new TgBot();
        try {
            telegramBotsApi.registerBot(tgBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

        return tgBot;
    }
}
