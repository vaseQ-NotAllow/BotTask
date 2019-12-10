package Bot;

import Messager.*;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;

public class Bot implements IMassageProcess, IPublisher {
    private BotCore bot;
    private ArrayList<IMassageProcess> processes = new ArrayList<>();

    public Bot(BotCore bot){
        this.bot = bot;
    }

    @Override
    public void Process(Message m) {
        Message output = bot.parse(m);
        for (IMassageProcess process :
                processes) {
            process.Process(output);
        }
    }

    @Override
    public void subscribe(IMassageProcess processor) {
        processes.add(processor);
    }
}

