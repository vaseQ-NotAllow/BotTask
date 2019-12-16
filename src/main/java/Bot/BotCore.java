package Bot;

import Generator.IGenerator;
import Messager.Message;
import Tasks.ITask;

import java.io.Console;
import java.util.HashMap;
import java.util.function.Function;

public class BotCore {
    private HashMap<String, ITask> users;
    private String info;
    private IGenerator taskGenerator;
    private String id;

    public BotCore(IGenerator generator, String info){
        this.info = info;
        taskGenerator = generator;
        users = new HashMap<String, ITask>(){};
    }

    public Message parse(Message m){
        String input = m.getText();
        id = m.getId().getChatId();
        if(input.equals("/task")){
            users.put(id, taskGenerator.getTask());
            return new Message(users.get(id).getCondition(), m.getId());
        }

        if(input.equals("/help"))
            return new Message(getInfo(), m.getId());
        if(users.containsKey(id)){
            if(users.get(id) == null){
                return new Message("You need to get task first", m.getId());
            }
            String answer = users.get(id).compare(input).toString();
            users.put(id, null);
            return new Message(answer, m.getId());
        }

        return new Message(getInfo(), m.getId());
    }

    public  String getInfo(){
        return  info;
    }
}
