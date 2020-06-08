package Generator;

import Tasks.ITask;
import Tasks.Task;
import Tasks.TaskChoseSeveral;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class SQLTaskGenerator implements IGenerator {
    private Integer lastTaskId;
    private ArrayList<ITask> taskList;
    private String userName;
    private String password;
    private String connectionUrl;

    public SQLTaskGenerator(String userName, String password, String connectionUrl){
        this.userName = userName;
        this.connectionUrl = connectionUrl;
        this.password = password;
    }

    @Override
    public ITask getTask(){
        try {
            return getTakFromDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    private ITask getTakFromDataBase()
    throws SQLException{
        if(taskList == null){
            taskList = new ArrayList<>();

            try(Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
                Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM test№2");
                while (resultSet.next()) {
                    ITask task;
                    if (resultSet.getBoolean("isSeveral")){
                        HashSet<Integer> answer = new HashSet<>();
                        for (String ans:
                                resultSet.getString("answer").split(" ")) {
                            answer.add(Integer.parseInt(ans));
                        }
                        task = new TaskChoseSeveral(resultSet.getString("t_condition"), answer);
                    } else
                        task = new Task(resultSet.getString("t_condition"), resultSet.getString("answer"));
                    taskList.add(task);
                }
            }

            lastTaskId = taskList.size() - 1;
        }

        lastTaskId = (lastTaskId + 1) % taskList.size();
        return taskList.get(lastTaskId);
    }
}
