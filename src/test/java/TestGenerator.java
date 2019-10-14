import org.junit.Assert;
import org.junit.Test;

public class TestGenerator {
    @Test
    public void TestGenerating(){
        TaskGenerator generator = new TaskGenerator();
        Task actual = generator.getTask();
        Assert.assertEquals("What the sum of 5+6?", actual.condition);
        Assert.assertEquals("11", actual.ans);
        actual = generator.getTask();
        Assert.assertEquals("Who was first egg or chicken?", actual.condition);
        Assert.assertEquals("Egg", actual.ans);
    }
}
