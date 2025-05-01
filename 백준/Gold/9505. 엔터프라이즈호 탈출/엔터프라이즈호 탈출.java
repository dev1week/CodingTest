import java.awt.*;
import java.math.BigInteger;
import java.util.*;
import java.io.*;
import java.util.List;

public class Main {

    private static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static final char START = 'E';
    private static final long MAX_DIST = (long) 10e12+1;

    private static final int[][] D = {{-1,0},{1,0},{0,-1},{0,1}};
    private static final int X = 0;
    private static final int Y = 1;


    public static void main(String[] args)throws IOException {
        int t = Integer.parseInt(buffer.readLine());

        StringBuilder result = new StringBuilder();

        //t*((v+e)loge+4*w);
        for(int test=0; test<t; test++){
            tokens = new StringTokenizer(buffer.readLine());
            int k = Integer.parseInt(tokens.nextToken());//전투기 종류
            int w = Integer.parseInt(tokens.nextToken());
            int h = Integer.parseInt(tokens.nextToken());
            char[][] map = new char[h][w];
            int[] costs = new int[26];

            Node start = null;

            for(int i=0; i<k; i++){
                tokens = new StringTokenizer(buffer.readLine());
                int battleShip = tokens.nextToken().charAt(0)-'A';
                int cost = Integer.parseInt(tokens.nextToken());
                costs[battleShip]= cost;
            }

            for(int x=0; x<h;x++){
                String line = buffer.readLine();
                for(int y=0; y<w; y++){
                    map[x][y] = line.charAt(y);
                    if(map[x][y]==START){
                        start = new Node(x,y,0);
                    }
                }
            }

            long[][] dist = new long[h][w];
            for(int x=0; x<h; x++){
                Arrays.fill(dist[x], MAX_DIST);
            }
            PriorityQueue<Node> que = new PriorityQueue<>();
            dist[start.x][start.y] = start.cost;
            que.add(start);
            while(!que.isEmpty()){
                Node current = que.poll();
                if(dist[current.x][current.y]!=current.cost) continue;
                for(int[] d:D ){
                    int nX = current.x+d[X];
                    int nY = current.y+d[Y];
                    if(inRange(nX, nY, w,h)){
                        int battleShip = map[nX][nY]-'A';
                        long newCost = dist[current.x][current.y]+costs[battleShip];
                        if(dist[nX][nY]>newCost){
                            que.add(new Node(nX, nY, newCost));
                            dist[nX][nY] = newCost;
                        }
                    }
                }
            }



            long minCost = MAX_DIST;

            for(int x=0; x<h; x++){
                minCost = Math.min(minCost, dist[x][0]);
                minCost = Math.min(minCost, dist[x][w-1]);
            }

            for(int y=0; y<w; y++){
                minCost = Math.min(minCost, dist[0][y]);
                minCost = Math.min(minCost, dist[h-1][y]);
            }
            result.append(minCost).append("\n");
        }
        System.out.println(result);
    }
    private static boolean inRange(int x, int y, int w, int h){
        return x>=0&&y>=0&&x<h&&y<w;
    }
}
class Node implements Comparable<Node> {
    int x, y;
    long cost;

    public Node(int x, int y, long cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.cost, o.cost);
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", cost=" + cost +
                '}';
    }
}

//분 10^5
//알파벳 종류 25
// 맵크기 10^6


