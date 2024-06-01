import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args)throws IOException{


        String data = buffer.readLine();

        int result = 0;
        int start = 0;

        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2= new Stack<>();

        for(int i=0; i<data.length(); i++){
            if(data.charAt(i)=='('){
                start = i;


                result = start;
                break;
            }
        }

        for(int i=start; i<data.length(); i++){
            char character = data.charAt(i);
            if(character=='('){

                stack1.add(character);

            }else if(character==')') {
                if (stack1.isEmpty()) {
                    stack2.add(character);
                } else {
                    stack1.pop();
                }
            }
        }




        System.out.println(Math.abs(stack1.size()+stack2.size())+result);

        // '('나올 때까지 센다.
            //센값은 바로 더한다.

        // '('
            //스택 2에 있는지 확인한다.
                //있으면? 스택2.poll
                //없으면? 스택1에 넣는다.


        // ')'
            // 스택1에 있는지 확인한다.
            //있으면? 스택1.poll
            //없으면? 스택2에 넣는다.



        //남아있는







    }

}
