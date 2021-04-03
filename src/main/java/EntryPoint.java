import Bot.*;
import Core.TaskProviderCore;
import Core.TasksCreatorCore;
import Generator.IGenerator;
import Sql.SQLTaskGenerator;
import Messager.TgBot;
import Sql.SqlConnectionProperties;
import Sql.SqlTableTaskCreator;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

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
        SqlConnectionProperties sqlConnectionProperties = new SqlConnectionProperties(System.getenv("username"), System.getenv("password"), System.getenv("connectionUrl"));
        IGenerator generator = new SQLTaskGenerator(sqlConnectionProperties);
        TaskProviderCore taskProviderCore = new TaskProviderCore(generator);
        SqlTableTaskCreator sqlTableTaskCreator = new SqlTableTaskCreator(sqlConnectionProperties);
        TasksCreatorCore tasksCreatorCore = new TasksCreatorCore(sqlTableTaskCreator);
        BotCore botCore = new BotCore(taskProviderCore, tasksCreatorCore);
        Bot bot = new Bot(botCore);
        tgBot.subscribe(bot);
        bot.subscribe(tgBot);
    }
}
