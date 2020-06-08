import Bot.BotCore;
import Generator.TaskGenerator;
import Messager.TgChatId;
import Messager.Message;
import Messager.TgChatId;
import org.junit.Assert;
import org.junit.Test;

public class BotCoreTest {
    private void message_Assert(Message excepted, Message actual){
        Assert.assertEquals(excepted.getText(), actual.getText());
        Assert.assertEquals(((TgChatId)excepted.getId()).getId(), ((TgChatId)actual.getId()).getId());
    }
    @Test
    public void test_Parse_ShouldReturnTaskCondition_IfGetCommandTask(){
        BotCore botCore = new BotCore(new TaskGenerator("src/main/resources/Tasks.properties"), "Test");
        Message input = new Message("/task", new TgChatId(12));
        Message excepted = new Message("What the sum of 5+6?", new TgChatId(12));

        Message actual = botCore.parse(input);

        message_Assert(excepted, actual);
    }

    @Test
    public void parse_ShouldReturnInfo_IfGetFirstNotCommandMessage(){
        BotCore botCore = new BotCore(new TaskGenerator("src/main/resources/Tasks.properties"), "Test");
        Message input = new Message("task", new TgChatId(12));
        Message excepted = new Message("Test", new TgChatId(12));

        Message actual = botCore.parse(input);

        message_Assert(excepted, actual);
    }

    @Test
    public void parse_ShouldReturnInfo_IfGetHelpCommand(){
        BotCore botCore = new BotCore(new TaskGenerator("src/main/resources/Tasks.properties"), "Test");
        Message input = new Message("/help", new TgChatId(12));
        Message excepted = new Message("Test", new TgChatId(12));

        Message actual = botCore.parse(input);

        message_Assert(excepted, actual);
    }

    @Test
    public void parse_ShouldReturnMessageAboutGettingNewTask_IfGetMessageAfterTakingAnswer(){
        BotCore botCore = new BotCore(new TaskGenerator("src/main/resources/Tasks.properties"), "Test");
        Message task = new Message("/task", new TgChatId(12));
        Message input = new Message("asd", new TgChatId(12));
        Message excepted = new Message("You need to get task first", new TgChatId(12));

        botCore.parse(task);
        botCore.parse(input);
        Message actual = botCore.parse(input);

        message_Assert(excepted, actual);
    }

    @Test
    public void core_ShouldCorrectlyWork_WithSeveralUsers(){
        BotCore botCore = new BotCore(new TaskGenerator("src/main/resources/Tasks.properties"), "Test");
        Message firs_user_get_task = new Message("/task", new TgChatId(12));
        Message second_user_put_answer = new Message("11", new TgChatId(1));
        Message excepted_first_get_task = new Message("What the sum of 5+6?", new TgChatId(12));
        Message excepted_second_get_info = new Message("Test", new TgChatId(1));

        Message actual1 = botCore.parse(firs_user_get_task);
        Message actual2 = botCore.parse(second_user_put_answer);

        message_Assert(excepted_first_get_task, actual1);
        message_Assert(excepted_second_get_info, actual2);
    }
}