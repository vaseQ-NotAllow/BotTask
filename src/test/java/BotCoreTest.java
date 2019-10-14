import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BotCoreTest {
    private static String info = "Hi! I am the bot -12 lvl 0.o....\n" +
            " I can give you some task, but i`m not sure, that a understand your answer right";
    @Test
    public void parseUserInputHelp() {
        String input = "\\help";
        Assert.assertEquals(info, BotCore.parseUserInput(input, ""));
    }

    @Test
    public void parseUserInputRightAnswer() {
        String input = "No";
        Assert.assertEquals("Correct\n", BotCore.parseUserInput(input, "No"));
    }

    @Test
    public void parseUserInputWrongAnswer() {
        String input = "Yes";
        Assert.assertEquals("Incorrect\n", BotCore.parseUserInput(input, "No"));
    }

    @Test
    public void getInfo() {
        Assert.assertEquals(info, BotCore.getInfo());
    }
}