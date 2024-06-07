import java.io.*;
import java.util.*;

class Node{

    int num, dist;


    public Node(int num, int dist){
        this.num = num;
        this.dist = dist;
    }

    public String toString(){
        return new StringBuilder().append(this.dist).append("->").append(this.num).toString();
    }
}


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    //루트는 항상 1번 정점
    static final int ROOT = 1;

    static List<Integer>[] graph;
    static int count;
    static boolean[] isVisited;
    static int[] targetColors;

    static void init() throws IOException{
        int n = Integer.valueOf(buffer.readLine());
        count = 0;
        graph = new List[n+1];
        isVisited = new boolean[n+1];
        targetColors = new int[n+1];


        tokens = new StringTokenizer(buffer.readLine());
        for(int node=1; node<=n; node++){
            int targetColor = Integer.valueOf(tokens.nextToken());

            targetColors[node] = targetColor;

            graph[node] = new ArrayList<>();
        }

        for(int edge=0; edge<n-1; edge++){
            tokens = new StringTokenizer(buffer.readLine());

            int start = Integer.valueOf(tokens.nextToken());
            int end = Integer.valueOf(tokens.nextToken());

            graph[start].add(end);
            graph[end].add(start);
        }







    }

    static void dfs(int currentNode, int currentColor){
        if(targetColors[currentNode]!=currentColor){
            count++;
            currentColor= targetColors[currentNode];
        }

        for(int nextNode: graph[currentNode]){
            if(!isVisited[nextNode]){
                isVisited[nextNode] = true;

                dfs(nextNode, currentColor);

            }
        }
    }




    public static void main(String[] args)throws IOException{
        init();
        isVisited[ROOT] = true;
        dfs(ROOT, 0);

        System.out.println(count);
    }

}
