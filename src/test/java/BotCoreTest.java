import Bot.BotCore;
import Generator.TaskGenerator;
import Messager.ChatId;
import Messager.Message;
import org.junit.Assert;
import org.junit.Test;

public class BotCoreTest {
    @Test
    public void parse_ShouldReturnTaskCondition_IfGetCommandTask(){
        BotCore botCore = new BotCore(new TaskGenerator("src/main/resources/Tasks.properties"), "Test");
        Message input = new Message("/task", new ChatId("test"));
        Message excepted = new Message("What the sum of 5+6?", new ChatId("test"));

        Message actual = botCore.parse(input);

        Assert.assertEquals(excepted, actual);
    }

    @Test
    public void parse_ShouldReturnInfo_IfGetFirstNotCommandMessage(){
        BotCore botCore = new BotCore(new TaskGenerator("src/main/resources/Tasks.properties"), "Test");
        Message input = new Message("task", new ChatId("test"));
        Message excepted = new Message("Test", new ChatId("test"));

        Message actual = botCore.parse(input);

        Assert.assertEquals(excepted, actual);
    }

    @Test
    public void parse_ShouldReturnInfo_IfGetHelpCommand(){
        BotCore botCore = new BotCore(new TaskGenerator("src/main/resources/Tasks.properties"), "Test");
        Message input = new Message("/help", new ChatId("test"));
        Message excepted = new Message("Test", new ChatId("test"));

        Message actual = botCore.parse(input);

        Assert.assertEquals(excepted, actual);
    }

    @Test
    public void parse_ShouldReturnMessageAboutGettingNewTask_IfGetMessageAfterTakingAnswer(){
        BotCore botCore = new BotCore(new TaskGenerator("src/main/resources/Tasks.properties"), "Test");
        Message task = new Message("/task", new ChatId("test"));
        Message input = new Message("asd", new ChatId("test"));
        Message excepted = new Message("You need to get task first", new ChatId("test"));

        botCore.parse(task);
        botCore.parse(input);
        Message actual = botCore.parse(input);

        Assert.assertEquals(excepted, actual);
    }
}