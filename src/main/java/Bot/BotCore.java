package Bot;

import Generator.IGenerator;
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

    public String parseUserInput(String input, String id){
        if (id != null)
            return parse(input, id);
        if(input.charAt(0) != '@')
            return "Message should start with @username";
        int endOfId = input.indexOf(' ');
        if (endOfId == -1)
            return "Message must have next form \"@username input(Command)\"";
        return parse(input.substring(endOfId + 1), input.substring(0,endOfId));
//        String outPut = users.get(currentUser).compare(input).toString();
//        users.put(currentUser, null);
//        return outPut;
    }

    private String parse(String input, String id){
        this.id = id;
        if(input.equals("/task")){
            users.put(id, taskGenerator.getTask());
            return users.get(id).getCondition();
        }

        if(input.equals("/help"))
            return getInfo();
        if(users.containsKey(id)){
            if(users.get(id) == null){
                return "You need to get task first";
            }
            String answer = users.get(id).compare(input).toString();
            users.put(id, null);
            return answer;
        }

        return "/task to start work";
    }

    public  String getInfo(){
        return  info;
    }

    public String getId(){
        return id;
    }
}
