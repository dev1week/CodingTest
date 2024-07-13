import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    //상태
    static List<Integer>[] graph;
    static int[] d;



    public static void main(String[] args )throws IOException{
        tokens = new StringTokenizer(buffer.readLine());
        int n = Integer.parseInt(tokens.nextToken());
        int root = Integer.parseInt(tokens.nextToken());
        int q = Integer.parseInt(tokens.nextToken());

        graph = new List[n+1];

        for(int node=1; node<=n; node++){
            graph[node] = new ArrayList<>();
        }

        for(int edge=0; edge<n-1; edge++){
            tokens = new StringTokenizer(buffer.readLine());

            int node1 = Integer.parseInt(tokens.nextToken());
            int node2 = Integer.parseInt(tokens.nextToken());

            graph[node1].add(node2);
            graph[node2].add(node1);
        }

        //d[i] = i를 루트로하는 서브트리에 속한 정점의 수
        d = new int[n+1];

        dfs(root, -1);
        StringBuilder result = new StringBuilder();

        for(int query=0; query<q; query++){
            int num = Integer.parseInt(buffer.readLine());
            result.append(d[num]).append("\n");
        }
        System.out.println(result);
    }

    private static void dfs(int current, int parent){
        d[current] = 1;

        for(int child : graph[current]){
            if(child==parent) continue;

            dfs(child, current);
            d[current]+=d[child];
        }
    }


}