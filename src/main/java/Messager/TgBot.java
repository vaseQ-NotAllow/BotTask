package Messager;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.ArrayList;

public class TgBot extends TelegramLongPollingBot implements IPublisher, IMassageProcess{
    private ArrayList<IMassageProcess> processes = new ArrayList<>();
    private String botUsername;
    private String botToken;

    public TgBot(String botUserName, String botToken){
        this.botToken = botToken;
        botUsername = botUserName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText())
            for (IMassageProcess process:
                 processes) {
                process.Process(new Message(update.getMessage().getText(),
                        new TgChatId(update.getMessage().getChatId())));
            }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void subscribe(IMassageProcess process){
        processes.add(process);
    }

    @Override
    public void Process(Message m) {
        ChatId id = m.getId();
        if (id.getType().compareTo(ChatType.Telegram) != 0)
            return;
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(((TgChatId) id).getId());
        sendMessage.setText(m.getText());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
