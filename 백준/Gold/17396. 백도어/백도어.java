import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    private static final int CANT_SEE = 0;
    private static final int START = 0;

    private static final long MAX_DIST = 100000000001L;

    public static void main(String[] args)throws IOException{

        tokens = new StringTokenizer(buffer.readLine());
        int n = Integer.parseInt(tokens.nextToken());
        int m = Integer.parseInt(tokens.nextToken());

        int[] isVisible = new int[n];
        tokens = new StringTokenizer(buffer.readLine());

        for(int node=0; node<n; node++){
            isVisible[node] = Integer.parseInt(tokens.nextToken());
        }


        List<Edge>[] graph = new List[n];

        for(int node=0; node<n; node++){
            graph[node] = new ArrayList<>();
        }

        for(int edge=0; edge<m; edge++){
            tokens= new StringTokenizer(buffer.readLine());

            int node1 = Integer.parseInt(tokens.nextToken());
            int node2 = Integer.parseInt(tokens.nextToken());
            long dist = Long.parseLong(tokens.nextToken());

            if(node2==n-1&&isVisible[node1]==CANT_SEE){
                //node2가 끝점이고 node1이 안보이는 곳이면 연결 가능
                addEdge(node1, node2, dist, graph);
            }else if(isVisible[node2]==CANT_SEE&&isVisible[node1]==CANT_SEE){
                //node2 안보이고 node1도 안보이면 연결 가능
                addEdge(node2, node1, dist, graph);
            }else if(node1==n-1&&isVisible[node2]==CANT_SEE) {
                addEdge(node1, node2, dist, graph);
            }
        }



        long[] dist = new long[n];
        Arrays.fill(dist, MAX_DIST);
        dist[START] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0,0));

        while(!pq.isEmpty()){
            Edge current = pq.poll();


            if(dist[current.idx] != current.dist) continue;

            for(Edge next: graph[current.idx]) {



                long newDist = dist[current.idx] + next.dist;

                if (newDist < dist[next.idx]) {
                    //System.out.println(next);
                    dist[next.idx] = newDist;
                    pq.add(new Edge(next.idx, newDist));
                }
            }
        }


        //System.out.println(Arrays.toString(dist));

        System.out.println(dist[n-1]==MAX_DIST?-1:dist[n-1]);






    }

    private static void addEdge(int node1, int node2, long dist, List<Edge>[] graph) {
        //System.out.println(node1+" "+node2+"추가");
        graph[node1].add(new Edge(node2, dist));
        graph[node2].add(new Edge(node1 , dist));
    }


}
class Edge implements Comparable<Edge>{
    int idx;
        long dist;


    public Edge(int idx, long dist){
        this.idx = idx;
        this.dist = dist;
    }

    public int compareTo(Edge o){
        return Long.compare(this.dist, o.dist);
    }
    public String toString(){
        return this.dist+"->"+this.idx;
    }
}