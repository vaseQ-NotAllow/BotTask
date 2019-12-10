import Bot.BotCore;
import Generator.TaskGenerator;
import org.junit.Assert;
import org.junit.Test;

public class BotCoreTest {
    @Test
    public void parseUserInput_ShouldReturnWarningAboutNameInStartOfMessage_IfGetMessageWithoutName(){
        BotCore botCore = new BotCore(new TaskGenerator("src/main/resources/Tasks.properties"), "Test");
        String input = "asd";
        String excepted = "Message should start with @username";

        String actual = botCore.parseUserInput(input);

        Assert.assertEquals(excepted, actual);
    }

    @Test
    public void parseUserInput_ShouldReturnWarningAboutCommand_IfGetMessageOnlyWithUsername(){
        BotCore botCore = new BotCore(new TaskGenerator("src/main/resources/Tasks.properties"), "Test");
        String input = "@Test";
        String excepted = "Message must have next form \"@username input(Command)\"";

        String actual = botCore.parseUserInput(input);

        Assert.assertEquals(excepted, actual);
    }

    @Test
    public void parseUserInput_ShouldReturnTaskCondition_IfGetCommandTask(){
        BotCore botCore = new BotCore(new TaskGenerator("src/main/resources/Tasks.properties"), "Test");
        String input = "@Test /task";
        String excepted = "What the sum of 5+6?";

        String actual = botCore.parseUserInput(input);

        Assert.assertEquals(excepted, actual);
    }

    @Test
    public void parseUserInput_ShouldReturnInfo_IfGetFirstNotCommandMessage(){
        BotCore botCore = new BotCore(new TaskGenerator("src/main/resources/Tasks.properties"), "Test");
        String input = "@Test task";
        String excepted = "Test";

        String actual = botCore.parseUserInput(input);

        Assert.assertEquals(excepted, actual);
    }

    @Test
    public void parseUserInput_ShouldReturnInfo_IfGetHelpCommand(){
        BotCore botCore = new BotCore(new TaskGenerator("src/main/resources/Tasks.properties"), "Test");
        String input = "@Test /help";
        String excepted = "Test";

        String actual = botCore.parseUserInput(input);

        Assert.assertEquals(excepted, actual);
    }

    @Test
    public void parseUserInput_ShouldReturnMessageAboutGettingNewTask_IfGetMessageAfterTakingAnswer(){
        BotCore botCore = new BotCore(new TaskGenerator("src/main/resources/Tasks.properties"), "Test");
        String task = "@Test /task";
        String input = "@Test asd";
        String excepted = "You need to get task first";

        botCore.parseUserInput(task);
        botCore.parseUserInput(input);
        String actual = botCore.parseUserInput(input);

        Assert.assertEquals(excepted, actual);
    }
}