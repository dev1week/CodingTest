import java.io.*;
import java.util.*;



public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    static final int YES = 1;
    static final int NO = 0;

    static int[][] d;
    static List<Integer>[] graph;


    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(buffer.readLine());


        d = new int[n+1][2];
        graph = new List[n+1];


        for(int node=0; node<=n; node++){
            graph[node] = new ArrayList<>();
        }

        for(int edge=0; edge<n-1; edge++){
            tokens = new StringTokenizer(buffer.readLine());
            int node1 = Integer.parseInt(tokens.nextToken());
            int node2 = Integer.parseInt(tokens.nextToken());
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        dfs(1, -1);

        System.out.println(Math.min(d[1][YES], d[1][NO]));

    }



    //d[i][YES] = sum ( min(d[child][NO], d[child][YES]) + 1
    //d[i][NO] = sum(d[child][YES])
    private static void dfs(int current, int parent){
        d[current][YES] = 1;

        for(int child: graph[current]){
            if(child == parent) continue;

            dfs(child, current);

            d[current][YES] += Math.min(d[child][YES], d[child][NO]);
            d[current][NO] += d[child][YES];
        }

    }

}
