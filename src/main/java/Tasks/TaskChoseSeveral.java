package Tasks;

import java.util.HashSet;

public class TaskChoseSeveral implements ITask {
    private String condition;
    private HashSet<Integer> ans;

    public TaskChoseSeveral(String condition, HashSet<Integer> ans) {
        this.condition = condition;
        this.ans = ans;
    }

    @Override
    public String getCondition() {
        return condition;
    }

    @Override
    public String getAnswer() {
        return ans.toString();
    }

    @Override
    public Enum<ComparatorAnswer> compare(String userAnswer) {
        String[] ans = userAnswer.split(" ");
        HashSet<Integer> answer = new HashSet<>();
        for (String number :
                ans) {
            try {
                answer.add(Integer.parseInt(number));
            } catch (Exception e){
                return ComparatorAnswer.IncorrectInput;
            }
        }
        if(answer.equals(this.ans))
            return ComparatorAnswer.Correct;
        return ComparatorAnswer.Wrong;
    }
}
