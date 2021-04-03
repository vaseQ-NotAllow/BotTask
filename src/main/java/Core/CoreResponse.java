package Core;

import Messager.Message;

public class CoreResponse {
    private Message message;
    private CoreResponseStatus status;

    CoreResponse(Message message, CoreResponseStatus status){
        this.message = message;
        this.status = status;
    }

    public CoreResponseStatus getStatus(){
        return status;
    }

    public Message getMessage(){
        return message;
    }
}
