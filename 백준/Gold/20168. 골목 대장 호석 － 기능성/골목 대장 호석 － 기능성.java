import java.io.*;
import java.util.*;

class EndNode{
    int nodeNum, cost;

    public EndNode(int nodeNum, int cost){
        this.nodeNum = nodeNum;
        this.cost = cost;
    }

    @Override
    public String toString(){
        return "-"+this.cost+"->"+this.nodeNum;
    }
}

public class Main {

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static List<EndNode>graph[];
    static int nodeNum;
    static int edgeNum;

    static int result = Integer.MAX_VALUE;


    static void dfs(int end, int currentCost, boolean[] isVisited, int currentNode, int currentMaxCost){
        if(currentCost<0) return;
        if(currentNode==end){
            result = Math.min(result, currentMaxCost);
            return;
        }

        for(EndNode next : graph[currentNode]){
            if(!isVisited[next.nodeNum]){
                isVisited[next.nodeNum]= true;
                dfs(end,currentCost-next.cost,isVisited, next.nodeNum, Math.max(currentMaxCost, next.cost) );
                isVisited[next.nodeNum]= false;
            }
        }

    }


    public static void main(String[] args)throws IOException{
        tokens = new StringTokenizer(buffer.readLine());

        nodeNum = Integer.parseInt(tokens.nextToken());
        edgeNum = Integer.parseInt(tokens.nextToken());

        graph = new List[nodeNum+1];

        int start = Integer.parseInt(tokens.nextToken());
        int end = Integer.parseInt(tokens.nextToken());
        int currentCost = Integer.parseInt(tokens.nextToken());

        for(int node=1; node<=nodeNum; node++){
            graph[node] = new ArrayList<>();
        }

        for(int edge=0; edge<edgeNum; edge++){
            tokens = new StringTokenizer(buffer.readLine());
            int node1 = Integer.parseInt(tokens.nextToken());
            int node2 = Integer.parseInt(tokens.nextToken());
            int cost = Integer.parseInt(tokens.nextToken());

            graph[node1].add(new EndNode(node2, cost));
            graph[node2].add(new EndNode(node1, cost));

        }

        boolean[] isVisited = new boolean[nodeNum+1];

        isVisited[start] = true;


        dfs(end, currentCost, isVisited, start, 0);

        System.out.println(result==Integer.MAX_VALUE?-1:result);

    }
}
