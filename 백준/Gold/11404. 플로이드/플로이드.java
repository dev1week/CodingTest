import java.io.*;
import java.util.*;



public class Main {

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    static final int MAX = 10000000;
    public static void main(String[] args)throws IOException{


        int n = Integer.parseInt(buffer.readLine());
        int m = Integer.parseInt(buffer.readLine());

        int[][] minCosts = new int[n+1][n+1];

        for(int city=1; city<=n; city++){
            Arrays.fill(minCosts[city], MAX);
        }

        for(int city=1; city<=n; city++) minCosts[city][city] = 0;

        for(int bus=0; bus<m; bus++){
            tokens = new StringTokenizer(buffer.readLine());
            int startCity = Integer.parseInt(tokens.nextToken());
            int endCity = Integer.parseInt(tokens.nextToken());
            int cost = Integer.parseInt(tokens.nextToken());

            minCosts[startCity][endCity] = Math.min(minCosts[startCity][endCity], cost);
        }


        for(int stopover=1; stopover<=n; stopover++){
            for(int start=1; start<=n; start++){
                for(int end=1; end<=n; end++){
                    if(minCosts[start][end]>minCosts[start][stopover]+minCosts[stopover][end]){
                        minCosts[start][end] = minCosts[start][stopover]+minCosts[stopover][end];
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for(int x=1; x<=n; x++){
            for(int y=1; y<=n; y++){
                result.append(minCosts[x][y]==MAX?0:minCosts[x][y]).append(" ");
            }
            result.append("\n");
        }
        System.out.println(result);
    }
}