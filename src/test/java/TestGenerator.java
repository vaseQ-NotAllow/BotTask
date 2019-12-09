import Generator.TaskGenerator;
import Tasks.Task;
import org.junit.Assert;
import org.junit.Test;

public class TestGenerator {
    @Test
    public void TestGenerating(){
        TaskGenerator generator = new TaskGenerator("src/main/resources/Tasks.properties");
        Task actual = generator.getTask();
        Assert.assertEquals("What the sum of 5+6?", actual.getCondition());
        Assert.assertEquals("11", actual.getAnswer());
        actual = generator.getTask();
        Assert.assertEquals("Who was first egg or chicken?", actual.getCondition());
        Assert.assertEquals("Egg", actual.getAnswer());
    }
}
