package Tasks;

public interface ITask {
    String getCondition();
    Enum<ComparatorAnswer> compare(String userAnswer);
}
