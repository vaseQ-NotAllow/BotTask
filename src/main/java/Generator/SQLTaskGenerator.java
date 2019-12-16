package Generator;

import Tasks.Task;

import java.sql.*;
import java.util.ArrayList;

public class SQLTaskGenerator implements IGenerator {
    private Integer lastTaskId;
    private ArrayList<Task> taskList;

    public SQLTaskGenerator() throws SQLException, ClassNotFoundException {
        taskList = new ArrayList<Task>();
        String userName = "root";
        String password = "root";
        String connectionUrl = "jdbc:mysql://localhost:3306/bottask";
        Class.forName("com.mysql.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
            Statement statement = connection.createStatement()) {
            System.out.println("We have SQL connection!");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Task");
            while (resultSet.next()) {
                Task task = new Task(resultSet.getString("condition"), resultSet.getString("answer"));
                taskList.add(task);
            }
        }
        lastTaskId = taskList.size() - 1;
    }
    @Override
    public Task getTask() {
        lastTaskId = (lastTaskId + 1) % taskList.size();
        return taskList.get(lastTaskId);
    }
}
