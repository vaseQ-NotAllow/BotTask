package Creator;

import Tasks.TasksTable;

import java.sql.SQLException;

public interface ITaskTableCreator {
    void CreateTaskTable(TasksTable tasksTable) throws SQLException;
}
