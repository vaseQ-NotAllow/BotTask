public class BotCore {
    public static String parseUserInput(String input, String answer){
        if(input.equals("\\help")){
            return getInfo();
        }
        if(Comparator.compare(input, answer)){
            return "Correct\n";
        }
        return "Incorrect\n";
    }

    public static String getInfo(){
        return  "Hi! I am the bot -12 lvl 0.o....\n" +
                " I can give you some task, but i`m not sure, that a understand your answer right";
    }
}
