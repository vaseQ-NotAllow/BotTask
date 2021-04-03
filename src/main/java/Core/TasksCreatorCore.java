package Core;

import Creator.ITaskTableCreator;
import Messager.ChatId;
import Messager.Message;
import Tasks.Task;
import Tasks.TasksTable;

import java.sql.SQLException;
import java.util.HashMap;


public class TasksCreatorCore implements ICore {
    private static final String INIT_COMMAND = "/creator";
    private static final String CORE_INFO =
            "/creator to enter tasks creator core" +
                    "\n /tasks {tasksName} - to create new tasksTable" +
                    "\n   /add {condition}#{answer} - to add tasks" +
                    "\n   /end - to save taskTable" +
                    "\n /exit - from this block";
    private HashMap<ChatId, TasksTable> userTasksTable;
    private ITaskTableCreator taskTableCreator;


    public TasksCreatorCore(ITaskTableCreator taskTableCreator) {
        this.taskTableCreator = taskTableCreator;
        userTasksTable = new HashMap<>();
    }

    public CoreResponse execute(Message message) {
        String input = message.getText();
        ChatId id = message.getId();

        if (input.equals(INIT_COMMAND))
            return new CoreResponse(new Message(CORE_INFO, id), CoreResponseStatus.Create);

        if (input.equals("/exit")) {
            userTasksTable.remove(id);
            return new CoreResponse(new Message("", id), CoreResponseStatus.Exit);
        }

        if (userTasksTable.containsKey(id)) {
            TasksTable userTasks = userTasksTable.get(id);
            if (input.startsWith("/add")) {
                String[] args = input.split(" ", 2)[1].split("#");
                if (args.length != 2)
                    return new CoreResponse(new Message("Invalid input", id), CoreResponseStatus.Continue);
                userTasks.AddTask(new Task(args[0], args[1]));
                return new CoreResponse(new Message("/add to add next task, /end to save tasks", id), CoreResponseStatus.Continue);
            }
            if (input.startsWith("/end"))
                try {
                    taskTableCreator.CreateTaskTable(userTasks);
                } catch (SQLException e) {
                    return new CoreResponse(new Message("Something went wrong, ask administration for help", id), CoreResponseStatus.Continue);
                }
            userTasksTable.remove(id);
            return new CoreResponse(new Message("Successful, create table", id), CoreResponseStatus.Continue);
        }

        if (input.startsWith("/tasks")) {
            String[] args = input.split(" ");
            if (args.length != 2)
                return new CoreResponse(new Message("Invalid input", id), CoreResponseStatus.Continue);
            userTasksTable.put(id, new TasksTable(args[1]));
            return new CoreResponse(new Message("/add to add task", id), CoreResponseStatus.Continue);
        }

        return new CoreResponse(new Message(getCoreInfo(), id), CoreResponseStatus.Continue);
    }

    @Override
    public String getCoreInfo() {
        return CORE_INFO;
    }

    @Override
    public boolean isInitialCommand(String command) {
        return command.equals(INIT_COMMAND);
    }
}
