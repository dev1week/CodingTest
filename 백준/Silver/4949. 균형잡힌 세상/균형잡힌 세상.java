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
        while(!(sentence = buffer.readLine()).equals(".")){

            char[] data = sentence.toCharArray();
            Stack<Character> stack = new Stack<>();


            result.append(test(data, stack)).append("\n");
        }
        System.out.println(result);
    }

    private static String test(char[] data, Stack<Character> stack) {
        for(char c: data){
            if(c == SMALL_START){
                stack.add(c);
            }else if(c == SMALL_END){
                if (!isPossible(stack, SMALL_END)) return NO;
            }else if(c== BIG_START){
                stack.add(c);
            }else if(c==BIG_END){
                if (!isPossible(stack, BIG_END)) return NO;
            }
        }

        if(stack.size()!=0||stack.size()!=0){
            return NO;
        }else{
            return YES;
        }
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