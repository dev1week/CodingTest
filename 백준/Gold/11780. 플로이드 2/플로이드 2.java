import java.io.*;
import java.util.*;



public class Main {

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static final int CANT_GO = 10000002;;
    static final int ARRIVED = 0;

    public static void main(String[] args)throws IOException{
        int n = Integer.parseInt(buffer.readLine());
        int m = Integer.parseInt(buffer.readLine());

        int[][] costs = new int[n+1][n+1];
        int[][] nxt = new int[n+1][n+1];

        for(int city=0; city<=n; city++){
            Arrays.fill(nxt[city], CANT_GO);
            Arrays.fill(costs[city], CANT_GO);
        }

        for(int city=1; city<=n; city++){
            nxt[city][city] = ARRIVED;
            costs[city][city] = ARRIVED;
        }

        for(int bus=0; bus<m; bus++){
            tokens = new StringTokenizer(buffer.readLine());
            int start = Integer.parseInt(tokens.nextToken());
            int end = Integer.parseInt(tokens.nextToken());
            int cost = Integer.parseInt(tokens.nextToken());

            costs[start][end] = Math.min(costs[start][end], cost);
            nxt[start][end] = end;
        }

        for(int stopover=1; stopover<=n; stopover++){
            for(int start=1; start<=n; start++){
                for(int end=1; end<=n; end++){
                    if(costs[start][end]>costs[start][stopover]+costs[stopover][end]){
                        costs[start][end] = costs[start][stopover]+costs[stopover][end];
                        nxt[start][end] = nxt[start][stopover];
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();

        for(int city1=1; city1<=n; city1++){
            for(int city2=1; city2<=n; city2++){
                result.append(costs[city1][city2]==CANT_GO?0:costs[city1][city2]).append(" ");
            }result.append("\n");
        }

        for(int start=1;start<=n; start++){
            for(int end=1; end<=n; end++){
                StringBuilder route = new StringBuilder();

                int cnt = 0;
                int current = start;
                while(current!=ARRIVED&&current!=CANT_GO){
                    route.append(current).append(" ");
                    current = nxt[current][end];
                    cnt++;
                }
                if(cnt!=1){
                    result.append(cnt).append(" ").append(route).append("\n");
                }else{
                    result.append("0\n");
                }
            }
        }
        System.out.println(result);
    }
}