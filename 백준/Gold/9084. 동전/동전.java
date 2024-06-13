import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {



        int T = Integer.valueOf(buffer.readLine());
        StringBuilder result = new StringBuilder();

        for(int test = 1; test <= T; test++) {
            int n = Integer.valueOf(buffer.readLine());
            int[] coins = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int targetTot = Integer.valueOf(buffer.readLine());

            int[] dp = new int[targetTot + 1];
            dp[0] = 1;  // 0원을 만드는 방법은 1가지

            for(int coin : coins) {
                for(int tot = coin; tot <= targetTot; tot++) {
                    dp[tot] += dp[tot - coin];
                }
            }

            result.append(dp[targetTot]).append("\n");
        }
        System.out.println(result);


        //dp[j] = 동전의 총합이 j일 경우 총합






    }



}
