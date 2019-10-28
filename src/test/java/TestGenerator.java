import Generator.TaskGenerator;
import Tasks.Task;
import org.junit.Assert;
import org.junit.Test;

public class TestGenerator {
    @Test
    public void TestGenerating(){
        TaskGenerator generator = new TaskGenerator();
        Task actual = generator.getTask();
        Assert.assertEquals("What the sum of 5+6?", actual.getCondition());
        Assert.assertEquals("11", actual.getAns());
        actual = generator.getTask();
        Assert.assertEquals("Who was first egg or chicken?", actual.getCondition());
        Assert.assertEquals("Egg", actual.getAns());
    }
}
