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

    static List<Node>[] graph;
    static boolean[] isVisited;
    //정점 개수 v




    public static void main(String[] args)throws IOException{

        int n = Integer.valueOf(buffer.readLine());
        graph = new List[n+1];
        graph[0] = new ArrayList<>();

        for(int node=1; node<=n;node++) {
            graph[node] = new ArrayList<>();
        }
        for(int node=1; node<=n; node++){
            tokens = new StringTokenizer(buffer.readLine());

            int start = Integer.valueOf(tokens.nextToken());

            while(true){
                int end = Integer.valueOf(tokens.nextToken());
                if(end==-1) break;
                int dist = Integer.valueOf(tokens.nextToken());

                graph[start].add(new Node(end, dist));
            }
        }




        Node farthestNodeStart = getFarthestNodeFrom(1, n);


        Node farthestNodeEnd = getFarthestNodeFrom(farthestNodeStart.num, n);


        System.out.println(farthestNodeEnd.dist);



    }

    static Node getFarthestNodeFrom(int startNode, int n){
        isVisited = new boolean[n+1];
        farthestNode = new Node(0,0);
        isVisited[startNode] = true;
        dfs(startNode, 0);
        return farthestNode;
    }

    static Node farthestNode;
    static void dfs(int currentNode, int totDist){
        if(farthestNode.dist<totDist){
            farthestNode = new Node(currentNode, totDist);
        }


        for(Node nextNode : graph[currentNode]){
            if(!isVisited[nextNode.num]){
                isVisited[nextNode.num] = true;

                dfs(nextNode.num, totDist+nextNode.dist);

            }

        }


    }

}
