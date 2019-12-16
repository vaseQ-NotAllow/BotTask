package Bot;

import Messager.*;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;

public class Bot implements IMassageProcess, IPublisher {
    private BotCore bot;
    private IMassageProcess subsciber;

    public Bot(BotCore bot){
        this.bot = bot;
    }

    @Override
    public void Process(Message m) {
        Message output = bot.parse(m);
        subsciber.Process(output);
    }

    @Override
    public void subscribe(IMassageProcess processor) {
            subsciber = processor;
    }
}

