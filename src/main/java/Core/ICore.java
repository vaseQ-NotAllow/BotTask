package Core;

import Messenger.Message;

public interface ICore {
    CoreResponse execute(Message message);

    String getCoreInfo();

    boolean isInitialCommand(String command);
}
