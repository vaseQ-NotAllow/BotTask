package Tasks;

public class Task implements ITask{
    private String condition;
    private String ans;

    public Task(String condition, String ans) {
        this.condition = condition;
        this.ans = ans;
    }

    public String getCondition() {
        return condition;
    }

    public Enum<ComparatorAnswer> compare(String userAnswer){
        if (userAnswer.compareToIgnoreCase(ans) == 0)
            return ComparatorAnswer.Correct;
        return ComparatorAnswer.Wrong;
    }
}
