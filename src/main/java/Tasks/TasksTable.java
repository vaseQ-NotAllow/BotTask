package Tasks;

import java.util.ArrayList;
import java.util.List;

public class TasksTable {
    private List<ITask> tasks;
    private int currentTaskIndex;
    private String taskTableName;

    public TasksTable(List<ITask> tasks, String taskTableName) {
        currentTaskIndex = 0;
        this.tasks = tasks;
        this.taskTableName = taskTableName;
    }

    public TasksTable(String taskTableName) {
        this.taskTableName = taskTableName;
        tasks = new ArrayList<>();
    }

    public ITask GetNextTask() {
        if (++currentTaskIndex == tasks.size())
            return null;
        return tasks.get(currentTaskIndex);
    }

    public ITask GetCurrentTask() {
        if (currentTaskIndex == tasks.size())
            return null;
        return tasks.get(currentTaskIndex);
    }

    public void AddTask(ITask task) {
        tasks.add(task);
    }

    public ITask GetTask(int index) {
        return tasks.get(index);
    }

    public String getTaskTableName() {
        return taskTableName;
    }
}
