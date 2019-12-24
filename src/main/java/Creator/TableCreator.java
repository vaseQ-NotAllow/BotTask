package Creator;

import Tasks.Task;

import java.sql.*;

public class TableCreator {
    public void createTable(String tableName, String[] tasks)
            throws SQLException {
        try(Connection connection = DriverManager.getConnection(System.getenv("connectionUrl"), System.getenv("userName"), System.getenv("password"));
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(String.format("CREATE TABLE %s(" +
                    "task_id INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "t_condition TEXT NOT NULL,\n" +
                    "answer TEXT NOT NULL,\n" +
                    "CONSTRAINT task_pk PRIMARY KEY (task_id));", tableName));
            for (int i = 0; i < tasks.length - 1; i+=2){
                statement.executeUpdate(String.format("INSERT INTO %s (t_condition, answer) \n" +
                        " VALUES (\'%s\', \'%s\');", tableName, tasks[i], tasks[i+1]));
            }
        }
    }
}
