package Bot;

import Messenger.*;

public class Bot implements IMessageProcess, IPublisher {
    private BotCore bot;
    private IMessageProcess subscriber;

    public Bot(BotCore bot) {
        this.bot = bot;
    }

    @Override
    public void Process(Message m) {
        Message output = bot.Parse(m);
        subscriber.Process(output);
    }

    @Override
    public void subscribe(IMessageProcess processor) {
        subscriber = processor;
    }
}

