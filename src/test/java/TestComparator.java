import org.junit.Assert;
import org.junit.Test;

public class TestComparator {
    @Test
    public void CompereSimilar(){
        Assert.assertTrue(Comparator.compare("val", "val"));
    }
    @Test
    public void CompereUnSimilar(){
        Assert.assertFalse(Comparator.compare("val", "vas"));
    }
}
