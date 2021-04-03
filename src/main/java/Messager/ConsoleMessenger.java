package Messager;

import java.util.Scanner;

public class ConsoleMessenger implements IMessageProcess, IPublisher {
    private IMessageProcess subscriber;
    private Scanner scan;

    public ConsoleMessenger() {
        scan = new Scanner(System.in);
    }

    private void Run() {
        String query = scan.next();
        subscriber.Process(new Message(query, new ConsoleChatId("consolka")));
    }

    @Override
    public void Process(Message m) {
        System.out.println(m.getText());
        Run();
    }

    @Override
    public void subscribe(IMessageProcess processor) {
        subscriber = processor;
    }
}
