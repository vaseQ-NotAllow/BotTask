public class Comparator {
    public static Boolean compare(String answer, String rightAnswer){
        return answer.compareToIgnoreCase(rightAnswer) == 0;
    }
}
