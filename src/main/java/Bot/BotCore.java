package Bot;

import Commands.ICommands;
import Generator.IGenerator;
import Tasks.ITask;

import java.util.HashMap;

public class BotCore {
    private String currentUser;
    private HashMap<String, ITask> users;
    private String info;
    private HashMap<String, ICommands> commands;
    private IGenerator taskGenerator;

    public BotCore(ICommands[] commands, IGenerator generator, String info){
        this.info = info;
        taskGenerator = generator;
        this.commands = new HashMap<String, ICommands>();
        users = new HashMap<String, ITask>(){};
        for (ICommands command:
             commands) {
            this.commands.put(command.getName(), command);
        }
    }

    public String parseUserInput(String input){
        if(input.charAt(0) == '/'){
            String[] command = input.split(" ");
            if(commands.containsKey(command[0]))
                return commands.get(command[0]).execute(this, command);
            else
                return "No such command";
        }

        if(users.get(currentUser) == null)
            return String.format("Wrong input, you gaven`t task, and command %s doesn`t exist", input);

        String outPut = users.get(currentUser).compare(input).toString();
        users.put(currentUser, null);
        return outPut;
    }

    public String changeCondition(String user){
        if(users.containsKey(user)){
            currentUser = user;
            return String.format("current user is %s", currentUser);
        }

        return "No such user";
    }

    public String addCondition(String user){
        if(!users.containsKey(user)){
            users.put(user, null);
            currentUser = user;
            return String.format("User %s, have been added", user);
        }
        return String.format("User %s, already existed", user);
    }

    public String removeCondition(){
        users.remove(currentUser);
        String outPut = String.format("User %s, have been removed", currentUser);
        currentUser = null;
        return outPut;
    }

    public String getCondition(){
        if(currentUser == null)
            return "You need to login, before getting task";

        if(users.get(currentUser) == null)
            users.put(currentUser, taskGenerator.getTask());

        return users.get(currentUser).getCondition();
    }

    public  String getInfo(){
        return  info;
    }
}
