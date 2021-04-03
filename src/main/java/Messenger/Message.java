package Messenger;

public class Message {
    private String text;
    private ChatId id;

    public Message(String text, ChatId id){
        this.text = text;
        this.id = id;
    }

    public String getText(){
        return text;
    }

    public ChatId getId(){
        return id;
    }
}
