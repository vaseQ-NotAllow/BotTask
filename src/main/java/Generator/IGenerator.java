package Generator;

import Tasks.TasksTable;

public interface IGenerator {
    TasksTable getTasks(String tasksSourceName);
}
