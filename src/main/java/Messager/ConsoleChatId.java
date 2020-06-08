package Messager;

public class ConsoleChatId extends ChatId {
    private String Id;

    public ConsoleChatId(String id){
        this.Id = id;
    }

    public String getId(){
        return Id;
    }

    @Override
    ChatType getType() {
        return ChatType.Console;
    }
}
