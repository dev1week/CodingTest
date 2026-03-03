//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;
import java.util.*;
import java.util.stream.*;

class Edge implements Comparable<Edge>{
    int idx;
    long dist;

    public Edge(int idx, long dist){
        this.idx = idx;
        this.dist = dist;
    }

    @Override
    public int compareTo(Edge o) {
        return Long.compare(this.dist, o.dist);
    }
}


class Main{
    private static BufferedReader buffer = new BufferedReader((new InputStreamReader(System.in)));
    private static StringTokenizer tokens;



    public static void main(String[] args) throws IOException{

        tokens = new StringTokenizer(buffer.readLine());

        int n = Integer.parseInt(tokens.nextToken());
        int e = Integer.parseInt(tokens.nextToken());

        List<Edge>[] graph = new List[n+1];
        for(int node=1; node<=n; node++){
            graph[node] = new ArrayList<>();
        }

        for(int edge=0; edge<e; edge++){
            tokens = new StringTokenizer(buffer.readLine());
            int node1 = Integer.parseInt(tokens.nextToken());
            int node2 = Integer.parseInt(tokens.nextToken());
            int dist = Integer.parseInt(tokens.nextToken());
            graph[node1].add(new Edge(node2, dist));
            graph[node2].add(new Edge(node1, dist));
        }

        tokens = new StringTokenizer(buffer.readLine());

        Graph g = new Graph(n,e, graph);

        int v1 = Integer.parseInt(tokens.nextToken());
        int v2 = Integer.parseInt(tokens.nextToken());

        long[] v1Distance = g.dijkstra(v1);
        long[] v2Distance = g.dijkstra(v2);

        //대충 없을 때 -1로 처리했다 치고
        long result1 = v1Distance[1]+v2Distance[v1]+v2Distance[n];
        long result2 = v2Distance[1]+v1Distance[v2]+v1Distance[n];




        System.out.println(Math.min(result1, result2)>=Integer.MAX_VALUE? -1: Math.min(result1, result2));


    }


}

class Graph{
    private int n, e;
    private List<Edge>[] graph;

    public Graph(int n, int e, List<Edge>[] graph) {
        this.n = n;
        this.e = e;
        this.graph = graph;
    }


    public long[] dijkstra(int start){
        long[] dist = new long[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Edge> que = new PriorityQueue<>();
        que.add(new Edge(start, 0));

        while(!que.isEmpty()){
            Edge current = que.poll();

            if(dist[current.idx] < current.dist) continue;

            for(Edge child : graph[current.idx]){
                long newDist = child.dist + dist[current.idx];

                if(newDist<dist[child.idx]){
                    dist[child.idx] = newDist;
                    que.add(new Edge(child.idx, newDist));
                }
            }
        }

        return dist;
    }
}

