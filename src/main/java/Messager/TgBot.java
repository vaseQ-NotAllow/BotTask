package Messager;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class TgBot extends TelegramLongPollingBot implements IMessager{
    private Queue<Update> updates = new ConcurrentLinkedQueue<>();
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText())
            updates.add(update);
    }

    @Override
    public String getBotUsername() {
        return "HelpTaskeBot";
    }

    @Override
    public String getBotToken() {
        return "991379286:AAG-L5l58W3692CtrmYrdMJ-kJY6ZZUTm44";
    }

    @Override
    public String read(){
        while (updates.isEmpty()){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Update update = updates.remove();
        return String.format("@%s %s", update.getMessage().getChatId(), update.getMessage().getText());
    }

    @Override
    public void write(String output, String id) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(id);
        sendMessage.setText(output);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
