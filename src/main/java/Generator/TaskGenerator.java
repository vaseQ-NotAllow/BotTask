package Generator;

import Generator.IGenerator;
import Tasks.Task;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class TaskGenerator implements IGenerator {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private int last = 0;

    public TaskGenerator(String source){
        generateFromProperties(source);
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
            Task task = new Task(
                    property.getProperty(String.format("condition%d", i)),
                    property.getProperty(String.format("answer%d", i))
            );
            tasks.add(task);
            i++;
        }
    }

    public Task getTask() {
        last = last % tasks.size();
        return tasks.get(last++);
    }
}
