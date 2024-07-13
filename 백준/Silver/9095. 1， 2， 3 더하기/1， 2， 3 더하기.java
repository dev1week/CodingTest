import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    //상태



    public static void main(String[] args )throws IOException{

        int[] dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;  //  1 1 2
        dp[3]= 4; //  1 1 1


        for(int i=4; i<=10; i++){
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
        //dp[i] = i를 1,2,3의 합으로 나타내는 방법

        //dp[0] = o0
        //dp[1] = 1
        //dp[2] = 2
        //dp[3] = 3

        //dp[i] = dp[i-1] + dp[i-2] + dp[i-3]

        int test = Integer.parseInt(buffer.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test; t++){
            int n = Integer.parseInt(buffer.readLine());
            sb.append(dp[n]).append("\n");
        }

        System.out.println(sb);

    }





}