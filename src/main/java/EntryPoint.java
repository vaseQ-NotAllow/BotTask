import Bot.*;
import Creator.TableCreator;
import Generator.IGenerator;
import Generator.SQLTaskGenerator;
import Generator.TaskGenerator;
import Messager.ConsoleMessager;
import Messager.IMassageProcess;
import Messager.Message;
import Messager.TgBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.sql.*;
import java.util.ArrayList;

public class EntryPoint {
    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        TgBot tgBot = new TgBot(System.getenv("botUsername"), System.getenv("botToken"));
        try {
            telegramBotsApi.registerBot(tgBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        //IGenerator generator = new TaskGenerator("src/main/resources/Tasks.properties");
        IGenerator generator = new SQLTaskGenerator(System.getenv("username"),
                System.getenv("password"),
                System.getenv("connectionUrl"));
        String info = "I am the bot, that can give you some tasks(/task)\n" +
                "You should sand me your message with @name in start of message(Not for telegram)\n" +
                "(/help) to repeat this message";
        BotCore botCore = new BotCore(generator, info);
        Bot bot = new Bot(botCore);
        tgBot.subscribe(bot);
        bot.subscribe(tgBot);
    }
}
