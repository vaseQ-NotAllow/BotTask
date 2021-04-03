package Sql;

import Generator.IGenerator;
import Tasks.ITask;
import Tasks.Task;
import Tasks.TaskChoseSeveral;
import Tasks.TasksTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class SQLTaskGenerator implements IGenerator {
    private SqlConnectionProperties sqlConnectionProperties;

    public SQLTaskGenerator(SqlConnectionProperties sqlConnectionProperties) {
        this.sqlConnectionProperties = sqlConnectionProperties;
    }

    @Override
    public TasksTable getTasks(String taskSourceName) {
        try {
            return getTakFromDataBase(taskSourceName);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private TasksTable getTakFromDataBase(String taskSourceName)
            throws SQLException {
        ArrayList<ITask> taskList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(sqlConnectionProperties.getConnectionUrl(),sqlConnectionProperties.getUserName(), sqlConnectionProperties.getPassword());
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s", taskSourceName));
            while (resultSet.next()) {
                ITask task;
                if (resultSet.getBoolean("isSeveral")) {
                    HashSet<Integer> answer = new HashSet<>();
                    for (String ans :
                            resultSet.getString("answer").split(" ")) {
                        answer.add(Integer.parseInt(ans));
                    }
                    task = new TaskChoseSeveral(resultSet.getString("t_condition"), answer);
                } else
                    task = new Task(resultSet.getString("t_condition"), resultSet.getString("answer"));
                taskList.add(task);
            }
        }
        return new TasksTable(taskList, taskSourceName);
    }
}
