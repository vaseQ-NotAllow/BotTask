import java.util.Scanner;

public class Bot {
    private IMessager messager;
    private IGenerator generator;

    public Bot(IMessager messager, IGenerator generator){
        this.messager = messager;
        this.generator = generator;
    }

    public void run(String[] args){
        messager.write(BotCore.getInfo());
        while (true){
            Task task = generator.getTask();
            messager.write(task.getCondition());
            String answer = messager.read();
            messager.write(BotCore.parseUserInput(answer, task.getAns()));
        }
    }
}
