import Generator.IGenerator;
import Generator.SQLTaskGenerator;
import Tasks.ITask;
import org.junit.Assert;
import org.junit.Test;

public class SQLTAskGeneratorTest {
    @Test
    public void Give_NotNullTask(){
        IGenerator generator = new SQLTaskGenerator(System.getenv("username"),
                System.getenv("password"),
                System.getenv("connectionUrl"));
        ITask task = generator.getTask();
        Assert.assertNotNull(task.getAnswer());
        Assert.assertNotNull(task.getCondition());
    }
}
