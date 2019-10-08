import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class TaskGenerator{
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private int last = 0;

    public TaskGenerator(){
        generateFromProperties("src/main/resources/Tasks.properties");
    }

    private void generateFromProperties(String propDir){
        Properties property = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream(propDir);
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
        while (property.getProperty(String.format("condition%d", i)) != null) {
            Task task = new Task();
            task.condition = property.getProperty(String.format("condition%d", i));
            task.ans = property.getProperty(String.format("answer%d", i));
            tasks.add(task);
            i++;
        }
    }

    public Task getTask(){
        if(last < tasks.size()){
            return tasks.get(last++);
        }
        return tasks.get(last = 0);
    }
}
