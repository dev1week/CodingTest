import java.io.*;
import java.util.*;



public class Main {

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    private static final char SMALL_START = '(';
    private static final char BIG_START = '[';
    private static final char SMALL_END = ')';
    private static final char BIG_END = ']';

    private static final String NO = "no";
    private static final String YES = "yes";


    public static void main(String[] args)throws IOException {
        String sentence;

        StringBuilder result = new StringBuilder();

        System.out.println(getResult(result));
    }

    private static String getResult(StringBuilder result) throws IOException {
        String sentence;
        while(!(sentence = buffer.readLine()).equals(".")){

            char[] data = sentence.toCharArray();
            Stack<Character> stack = new Stack<>();


            result.append(test(data, stack)).append("\n");
        }
        return result.toString();
    }

    private static String test(char[] data, Stack<Character> stack) {
        for (char c : data) {
            if (isStart(c)) {
                stack.add(c);
            }else if(isEnd(c)){
                if( !isPossible(stack, c)) return NO;
            }
        }

        if(!stack.isEmpty()){
            return NO;
        }else{
            return YES;
        }
    }

    private static boolean isEnd(char c) {
        return c == BIG_END || c == SMALL_END;
    }

    private static boolean isStart(char c) {
        return c == SMALL_START || c == BIG_START;
    }

    private static boolean isPossible( Stack<Character> stack, char data) {
        if(stack.size()==0){
            return false;
        }
        else{
            if(isValid(stack, data)){
                stack.pop();
                return true;
            }else{
                return false;
            }
        }
    }

    private static boolean isValid(Stack<Character> stack, char data) {
        if(data==BIG_END){
            if(stack.peek()==BIG_START){
                return true;
            }else{
                return false;
            }
        }else if(data==SMALL_END){
            if(stack.peek()==SMALL_START){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

}