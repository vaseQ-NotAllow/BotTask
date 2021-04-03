package Sql;

import Creator.ITaskTableCreator;
import Tasks.ITask;
import Tasks.TasksTable;

import java.sql.*;

public class SqlTableTaskCreator implements ITaskTableCreator {
    private SqlConnectionProperties sqlProperties;

    public SqlTableTaskCreator(SqlConnectionProperties sqlProperties) {
        this.sqlProperties = sqlProperties;
    }

    @Override
    public void CreateTaskTable(TasksTable tasksTable) throws SQLException {
        try (Connection connection = DriverManager.getConnection(sqlProperties.getConnectionUrl(), sqlProperties.getUserName(), sqlProperties.getPassword());
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(String.format("CREATE TABLE %s(" +
                    "task_id INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "t_condition TEXT NOT NULL,\n" +
                    "answer TEXT NOT NULL,\n" +
                    "isSeveral tinyint DEFAULT NULL,\n" +
                    "CONSTRAINT task_pk PRIMARY KEY (task_id));", tasksTable.getTaskTableName()));
            ITask task = tasksTable.GetCurrentTask();
            while (task != null){
                statement.executeUpdate(String.format("INSERT INTO %s (t_condition, answer) \n" +
                        " VALUES (\'%s\', \'%s\');", tasksTable.getTaskTableName(), task.getCondition(), task.getAnswer()));
                task = tasksTable.GetNextTask();
            }
        }
    }
}
