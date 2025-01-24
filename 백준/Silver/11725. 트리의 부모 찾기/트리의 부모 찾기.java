import org.w3c.dom.Node;

import java.io.*;
import java.util.*;



public class Main {

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    private final static int ROOT = 1;
    private static int[] parents;


    public static void main(String[] args) throws IOException{


        //연결리스트 입력을 받는다.
        int n = Integer.parseInt(buffer.readLine());
        parents = new int[n+1];
        List<Integer>[] graph = new List[n+1];
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


        //dfs로 모든 노드를 방문 후 노드의 부모 노드 배열을 만든다.
        dfs(ROOT, graph);

        System.out.println(printParents(parents, n));
    }

    private static void dfs(int current,List<Integer>[] graph){

        for(int child: graph[current]){
            if(parents[current]==child)continue;
            parents[child] = current;
            dfs(child, graph);
        }
    }

    private static String printParents(int[] parents, int n){
        int start=2;
        StringBuilder parentsString = new StringBuilder();

        for(int node=start; node<=n; node++){
            parentsString.append(parents[node]).append("\n");
        }

        return parentsString.toString();
    }
}