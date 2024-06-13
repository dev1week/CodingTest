import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {





        tokens = new StringTokenizer(buffer.readLine());

        int n = Integer.valueOf(tokens.nextToken());
        int k = Integer.valueOf(tokens.nextToken());


        int[] values = new int[k];
        int[] weights = new int[k];

        for(int i=0; i<k; i++){
            tokens = new StringTokenizer(buffer.readLine());


            values[i] = Integer.valueOf(tokens.nextToken());
            weights[i] = Integer.valueOf(tokens.nextToken());

        }


        //dp[j] 공부 시간합이 J일 때 과목의 중요도합 최대
        int[] dp = new int[n+1];

        for(int idx=0; idx<k; idx++){
            for(int totWeight=n; totWeight>=weights[idx]; totWeight--){
                if(totWeight>=weights[idx]) dp[totWeight] = Math.max(dp[totWeight], dp[totWeight-weights[idx]]+values[idx]);
            }
        }

        System.out.println(getMaxFromArray(dp));

    }


    private static int getMaxFromArray(int[] array){
        int result = 0;
        for(int data: array){
            result = Math.max(data, result);
        }
        return result;
    }
}
