package Core;

import Generator.IGenerator;
import Messager.ChatId;
import Messager.Message;
import Tasks.ITask;
import Tasks.TasksTable;

import java.util.HashMap;

public class TaskProviderCore implements ICore {
    private static final String initCommand = "/generator";
    private static final String coreInfo = "/generator - give different task" +
            "\n /tasks 'tasksName'- to get tasks" +
            "\n  /task to get next task from tasks" +
            "\n /exit";
    private HashMap<ChatId, TasksTable> users;
    private IGenerator taskGenerator;

    public TaskProviderCore(IGenerator generator) {
        users = new HashMap<ChatId, TasksTable>() {
        };
        taskGenerator = generator;
    }

    @Override
    public CoreResponse execute(Message message) {
        String input = message.getText();
        ChatId id = message.getId();

        if (input.equals(initCommand))
            return new CoreResponse(new Message(coreInfo, id), CoreResponseStatus.Create);

        if (input.equals("/exit")){
            users.remove(id);
            return new CoreResponse(new Message("", id), CoreResponseStatus.Exit);
        }

        if (users.containsKey(id)) {
            TasksTable userTasks = users.get(id);

            if (input.equals("/task")) {
                ITask nextTask = userTasks.GetNextTask();
                if (nextTask == null)
                    return new CoreResponse(new Message("This is end of с task. Get new /tasks", id), CoreResponseStatus.Continue);
                return new CoreResponse(new Message(nextTask.getCondition(), id), CoreResponseStatus.Continue);
            }

            String answer = userTasks.GetCurrentTask().compare(input).toString();

            return new CoreResponse(new Message(answer, id), CoreResponseStatus.Continue);
        }

        if (input.startsWith("/tasks")) {
            String[] args = input.split(" ");
            if (args.length != 2)
                return new CoreResponse(new Message("Invalid input", id), CoreResponseStatus.Continue);
            TasksTable tasks = taskGenerator.getTasks(args[1]);
            if (tasks == null)
                return new CoreResponse(new Message("Invalid tasksName", id), CoreResponseStatus.Continue);
            users.put(id, tasks);
            return new CoreResponse(new Message(tasks.GetCurrentTask().getCondition(), id), CoreResponseStatus.Continue);
        }

        return new CoreResponse(new Message(getCoreInfo(), id), CoreResponseStatus.Continue);
    }

    @Override
    public String getCoreInfo() {
        return coreInfo;
    }

    @Override
    public boolean isInitialCommand(String command) {
        return command.equals(initCommand);
    }
}