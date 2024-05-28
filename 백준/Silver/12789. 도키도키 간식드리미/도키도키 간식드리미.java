import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));


    //스택 1

    //스택 2

    public static void main(String[] args)throws IOException{
        //받아야할 사람의 번호 = 1


        boolean isPossible = true;
        int nextPersonNum = 1;


        LinkedList<Integer> stack1 = new LinkedList<>();
        Stack<Integer> stack2 = new Stack<>();


        int n = Integer.valueOf(buffer.readLine());
        StringTokenizer tokens = new StringTokenizer(buffer.readLine());


        for(int i=0; i<n; i++){
            stack1.add(Integer.valueOf(tokens.nextToken()));
        }


        while(!stack1.isEmpty()){
            if(nextPersonNum == stack1.getFirst()){
                nextPersonNum++;
                stack1.removeFirst();
            }else if(
                !stack2.isEmpty()&&nextPersonNum == stack2.peek()
            ){
                nextPersonNum++;
                stack2.pop();
            }
            else{
                if(!stack2.isEmpty()){
                    if(stack2.peek()<stack1.getFirst()){
                        isPossible = false;
                        break;
                    }else{
                        stack2.add(stack1.getFirst());
                        stack1.removeFirst();
                    }
                }else{
                    stack2.add(stack1.getFirst());
                    stack1.removeFirst();
                }

            }
        }

        if(isPossible){
            System.out.println("Nice");
        }else{
            System.out.println("Sad");
        }




        //스택 1에서 뽑기
            //받아야할 사람의 번호와 같은지 확인
                //같다
                    //정답 배열에 넣기
                    //받아야할 사람의 번호 ++;
                //다르다
                    //2의 맨 위의 값 확인하기
                        //현재 값보다 크다
                            //2에 넣기
                        //현재 값보다 작다
                            //break -> 실패










    }

}
