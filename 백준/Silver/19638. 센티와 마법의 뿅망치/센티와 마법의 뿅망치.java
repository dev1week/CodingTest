import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static final String YES = "YES";
    static final String NO = "NO";


    public static void main(String[] args)throws IOException{
         //뿅망치 -> 맞은사람의 키 / 2
        tokens = new StringTokenizer(buffer.readLine());


        int n= Integer.valueOf(tokens.nextToken()); // 거인 인구수
        long limit = Integer.valueOf(tokens.nextToken());
        int t= Integer.valueOf(tokens.nextToken());// 뿅망치 횟수제한


        PriorityQueue<Long> heights = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i<n; i++){
            heights.add(Long.valueOf(buffer.readLine()));
        }

        boolean isFinished = false;
        int result = 0;
        if(heights.peek()<limit){
            isFinished = true;
            result = 0;
        }else{
            for(int magic=1; magic<=t; magic++){
                long height = heights.poll();
                if(height!=1){
                    height/=2;
                }
                heights.add(height);

                if(heights.peek()<limit){
                    isFinished = true;
                    result = magic;
                    break;
                }
            }
        }


        StringBuilder sb = new StringBuilder();

       if(isFinished){
        sb.append(YES).append("\n").append(result);
       }else{
        sb.append(NO).append("\n").append(heights.peek());
       }

       System.out.println(sb);





    }



}
