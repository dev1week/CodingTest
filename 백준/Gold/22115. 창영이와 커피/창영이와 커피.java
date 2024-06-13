import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    private static final int NOT_EXIST = 100001;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(buffer.readLine());
        int n = Integer.valueOf(tokens.nextToken());
        int k = Integer.valueOf(tokens.nextToken());


        int[] c = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[k+1]; //dp[c] = 합계가 c일 때, 커피의 최소 갯수


        Arrays.fill(dp, NOT_EXIST);
        dp[0] = 0;


        //dp[c] = Min(dp[c], dp[c-c[i]]+1);


        for(int idx=0; idx<n; idx++){
            for(int totC=k; totC>=c[idx]; totC--){
                dp[totC] = Math.min(dp[totC], dp[totC-c[idx]]+1);
            }
        }

        if(dp[k]==NOT_EXIST){
            System.out.println(-1);
        }else{
            System.out.println(dp[k]);
        }










    }



}
