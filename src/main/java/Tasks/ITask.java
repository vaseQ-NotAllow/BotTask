package Tasks;

public interface ITask {
    String getCondition();
    String getAnswer();
    Enum<ComparatorAnswer> compare(String userAnswer);
}
