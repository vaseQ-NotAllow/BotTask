package Core;

import Messager.Message;

public interface ICore {
    CoreResponse execute(Message message);

    String getCoreInfo();

    boolean isInitialCommand(String command);
}
