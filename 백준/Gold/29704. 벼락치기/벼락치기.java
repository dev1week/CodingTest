import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(buffer.readLine());


        int n= Integer.valueOf(tokens.nextToken());//제출기한 T 1000
        int T= Integer.valueOf(tokens.nextToken()); //문제의 갯수 1000

        //벌금의 총액이 낮아지도록

        int[] reqDays = new int[n]; // i번째 문제를 풀 때 요구되는 일수
        int[] costs= new int[n];  // i번째 문제를 못풀었을 때 벌금
        int totCosts = 0;
        for(int i=0; i<n; i++){
            tokens = new StringTokenizer(buffer.readLine());
            reqDays[i] = Integer.valueOf(tokens.nextToken());
            costs[i] = Integer.valueOf(tokens.nextToken());
            totCosts += costs[i];
        }

        int[] dp = new int[T+1];

        for(int idx=0; idx<n; idx++){
            for(int totReqDays=T; totReqDays>=reqDays[idx]; totReqDays--){
                dp[totReqDays] = Math.max(dp[totReqDays], dp[totReqDays-reqDays[idx]]+costs[idx]);
            }
        }

        
        System.out.println(totCosts-dp[T]);

        //dp[j] 요구되는 일수 총합 j일 때 벌금 총합이 최대가 되는 경우

        //문제 종류 수 만큼 순회
            //제출기한 T~ 문제에서 요구한 기한
                //dp[j] = dp[j-reqDays[i]]+cost[i], dp[j]


        //전체 costs 총합- dp[j] 를 출력





    }



}
