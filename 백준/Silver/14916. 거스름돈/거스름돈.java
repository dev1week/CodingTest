
import javax.swing.text.Position;
import java.io.*;
import java.util.*;



public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)throws IOException {

        int n = Integer.valueOf(buffer.readLine());
        //DP[I]= 동전 합이 i일 때 최소 동전 갯수

        int[] coins = {2,5};
        int[] dp = new int[n+1];
        Arrays.fill(dp, n+1);

        dp[0] = 0;
        for(int sum=0; sum<=n; sum++){
            for(int coin :coins ){
                if(coin>sum)continue;

                dp[sum] = Math.min( dp[sum], dp[sum-coin]+1);
            }
        }
        if(dp[n]==n+1){
            System.out.println(-1);
        }else{
            System.out.println(dp[n]);
        }

    }
}
