package Generator;

import Tasks.ITask;
import Tasks.Task;
import Tasks.TasksTable;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TaskGenerator implements IGenerator {
    public TasksTable getTasks(String tasksSourceName) {
        List<ITask> tasks = new ArrayList<>();
        Properties property = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream(tasksSourceName);
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

        return new TasksTable(tasks, tasksSourceName);
    }
}
