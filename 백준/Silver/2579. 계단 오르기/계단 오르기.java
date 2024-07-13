import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    //상태



    public static void main(String[] args )throws IOException{
        int n= Integer.parseInt(buffer.readLine());

        int[] scores = new int[301];

        for(int i=1; i<=n; i++){
            scores[i] = Integer.parseInt(buffer.readLine());
        }

        int[][] d = new int[301][2];

        d[1][0] = 0;
        d[1][1] = scores[1];

        d[2][1] = scores[1]+scores[2];
        d[2][0] = scores[2];


        //d[i][0] = i-1번째 계단을 밟지 않았을 때의 총합 중 최대
        //d[i][1] = i-1번째 계단을 밟았을 때의 총합 중 최대

        //d[i][0] = d[i-2][1], d[i-2][0]
        //d[i][1] = d[i-1][0]


        for(int i=3; i<=n; i++){
            d[i][0] = Math.max(d[i-2][1], d[i-2][0]) + scores[i];
            d[i][1] = d[i-1][0]+scores[i];
        }



        System.out.println(Math.max(d[n][0], d[n][1]));
    }
}