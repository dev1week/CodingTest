import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    //상태
    static List<Integer>[] graph;
    static int[] peopleCount;
    static int[][] d;

    private static final int USED = 1;
    private static final int NOT_USED = 0;



    public static void main(String[] args )throws IOException{
        int n = Integer.parseInt(buffer.readLine());

        d = new int[n+1][2];

        peopleCount = new int[n+1];

        graph = new List[n+1];


        tokens = new StringTokenizer(buffer.readLine());
        for(int node=1; node<=n; node++){
            peopleCount[node] = Integer.parseInt(tokens.nextToken());
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




        System.out.println(Math.max(d[1][USED], d[1][NOT_USED]));
    }

    private static void dfs(int current, int parent){
        d[current][NOT_USED] = 0;
        d[current][USED] = peopleCount[current];

        int notUsedCount1 = 0;
        int notUsedCount2 = 0;

        for(int child: graph[current]){
            if(child == parent) continue;

            dfs(child, current);

            d[current][USED] += d[child][NOT_USED];
            d[current][NOT_USED] += Math.max(d[child][USED], d[child][NOT_USED]);

        }
    }




}