import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class AD{
    int cost, benefit;

    public AD(int cost, int benefit){
        this.cost = cost;
        this.benefit = benefit;
    }
}


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    public static void main(String[] args) throws IOException {

        //dp[비용] = 고객의 최대 증가수
        //d[0] = 0;
        //나머지 d = -1

        tokens = new StringTokenizer(buffer.readLine());

        int c = Integer.parseInt(tokens.nextToken());
        int n = Integer.parseInt(tokens.nextToken());

        AD[] ads = new AD[n];


        int maxCost = 0;
        for(int i = 0; i<n; i++){
            tokens = new StringTokenizer(buffer.readLine());
            int cost = Integer.parseInt(tokens.nextToken());
            int benefit = Integer.parseInt(tokens.nextToken());
            ads[i] = new AD(cost, benefit);
            maxCost = Math.max(maxCost, cost);
        }
        int[] d = new int[maxCost*1000+1];


        for(int totCost=0; totCost<=maxCost*1000; totCost++){
            for(AD ad: ads) {
                if (totCost - ad.cost < 0) continue;
                if (d[totCost - ad.cost] == -1) continue;
                d[totCost] = Math.max(d[totCost], d[totCost-ad.cost] + ad.benefit);
            }
        }

        for(int i = 0; i<=maxCost*1000; i++){
            if(d[i]>=c){
                System.out.println(i);
                break;
            }

        }



    }


}





