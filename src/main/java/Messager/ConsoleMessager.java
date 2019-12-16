package Messager;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleMessager implements IMassageProcess, IPublisher {
    private IMassageProcess subscriber;
    private Scanner scan;

    public ConsoleMessager() {
        scan = new Scanner(System.in);
    }

    public void Run() {
        String query = scan.next();
        subscriber.Process(new Message(query, new ChatId("consolka")));
    }

    @Override
    public void Process(Message m) {
        System.out.println(m.getText());
        Run();
    }

    @Override
    public void subscribe(IMassageProcess processor) {
        subscriber = processor;
    }
}
