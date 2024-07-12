import java.io.*;
import java.util.*;

class Point{
    int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;




    public static void main(String[] args )throws IOException{
        tokens = new StringTokenizer(buffer.readLine());

        int n = Integer.parseInt(tokens.nextToken());
        long k = Integer.parseInt(tokens.nextToken());
        Integer[] coins = new Integer[n];

        for(int i=0; i<n; i++){
            coins[i] = Integer.parseInt(buffer.readLine());
        }

        Arrays.sort(coins, Collections.reverseOrder());

        long count = 0;
        for(int coin : coins){
            if(k>=coin){
                long numOfCoin = k/coin;
                count += numOfCoin;
                k-= (numOfCoin*coin);
            }
        }
        System.out.println(count);

    }





}