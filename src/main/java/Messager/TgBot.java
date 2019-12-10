package Messager;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class TgBot extends TelegramLongPollingBot implements IPublisher, IMassageProcess{
    private ArrayList<IMassageProcess> processes = new ArrayList<>();

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText())
            for (IMassageProcess process:
                 processes) {
                process.Process(new Message(update.getMessage().getText(),
                        new ChatId(update.getMessage().getChatId().toString())));
            }
    }

    @Override
    public String getBotUsername() {
        return "HelpTaskeBot";
    }

    @Override
    public String getBotToken() {
        return "991379286:AAG-L5l58W3692CtrmYrdMJ-kJY6ZZUTm44";
    }

    public void subscribe(IMassageProcess process){
        processes.add(process);
    }


    @Override
    public void Process(Message m) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(m.getId().getChatId());
        sendMessage.setText(m.getText());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
