import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    static List<Integer>[] graph;
    static long[][] d;
    static long[][] costs;

    static final int BLACK = 1;
    static final int WHITE = 0;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(buffer.readLine());

        graph = new List[n];


        for(int node=0; node<n; node++){
            graph[node] = new ArrayList<>();
        }

        d = new long[n][2];
        costs = new long[n][2];


        for(int edge=0; edge<n-1; edge++){
            tokens = new StringTokenizer(buffer.readLine());
            int node1 = Integer.parseInt(tokens.nextToken());
            int node2 = Integer.parseInt(tokens.nextToken());

            graph[node1].add(node2);
            graph[node2].add(node1);
        }

        for(int node=0;  node<n; node++){
            tokens = new StringTokenizer(buffer.readLine());

            long whiteCost = Long.parseLong(tokens.nextToken());
            long blackCost = Long.parseLong(tokens.nextToken());

            costs[node][WHITE] = whiteCost;
            costs[node][BLACK] = blackCost;
        }

        dfs(0,-1);


        System.out.println(Math.min(d[0][WHITE], d[0][BLACK]));

    }

    private static void dfs(int current, int parent){
        d[current][WHITE] = costs[current][WHITE];
        d[current][BLACK] = costs[current][BLACK];
        for(int child : graph[current]){
            if(child==parent) continue;
            dfs(child, current);
            d[current][BLACK] += d[child][WHITE];
            d[current][WHITE] += Math.min(d[child][WHITE], d[child][BLACK]);
        }

    }





}
