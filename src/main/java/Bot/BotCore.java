package Bot;

import Messenger.ChatId;
import Messenger.Message;
import Core.*;

import java.util.HashMap;

public class BotCore {
    private ICore[] cores;
    private HashMap<ChatId, ICore> currentCore;
    private String info;

    public BotCore(ICore... cores) {
        this.cores = cores;
        currentCore = new HashMap<ChatId, ICore>() {
        };
        StringBuilder stringBuilder = new StringBuilder("Description\n");
        for (ICore core : cores) {
            stringBuilder.append("=======\n");
            stringBuilder.append(core.getCoreInfo()).append("\n");
        }
        info = stringBuilder.toString();
    }

    Message Parse(Message m) {
        if (currentCore.containsKey(m.getId())) {
            CoreResponse coreResponse = currentCore.get(m.getId()).execute(m);
            return ParseCoreResponse(coreResponse);
        }
        for (ICore core : cores) {
            if (core.isInitialCommand(m.getText())) {
                CoreResponse coreResponse = core.execute(m);
                if (coreResponse.getStatus() == CoreResponseStatus.Create)
                    currentCore.put(m.getId(), core);
                return ParseCoreResponse(coreResponse);
            }
        }
        return new Message(info, m.getId());
    }

    private Message ParseCoreResponse(CoreResponse coreResponse) {
        Message coreMessage = coreResponse.getMessage();
        if (coreResponse.getStatus() == CoreResponseStatus.Exit) {
            currentCore.remove(coreMessage.getId());
            return new Message(coreResponse.getMessage().getText() + "\n" + info, coreMessage.getId());
        }
        return coreMessage;
    }
}
