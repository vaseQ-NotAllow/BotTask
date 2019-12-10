package Messager;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface IMassageProcess {
    void Process(Message m);
}
