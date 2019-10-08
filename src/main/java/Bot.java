import java.util.Scanner;

public class Bot {
    private static String info = "Some info";

    public static void main(String[] args){
        System.out.println(info);
        TaskGenerator generator = new TaskGenerator();
        Comparator compare = new Comparator();
        Scanner scan = new Scanner(System.in);
        while (true){
            Task task = generator.getTask();
            System.out.println(task.condition);
            String answer = scan.nextLine();

        }
    }

    public static


}
