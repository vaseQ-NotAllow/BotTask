import java.util.Scanner;

public class Bot {
    public void run(String[] args){
        System.out.println(BotCore.getInfo());
        TaskGenerator generator = new TaskGenerator();
        Scanner scan = new Scanner(System.in);
        while (true){
            Task task = generator.getTask();
            System.out.println(task.condition);
            String answer = scan.nextLine();
            System.out.println(BotCore.parseUserInput(answer, task.ans));
        }
    }
}
