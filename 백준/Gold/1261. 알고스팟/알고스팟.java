

import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static final int[][] directions = {{-1,0}, {0,1}, {0,-1}, {1,0}};
    private static final int X = 0;
    private static final int Y = 1;

    private static final int MAX_DIST = 100*100+2;


    public static void main(String[] args)throws IOException {

        tokens = new StringTokenizer(buffer.readLine());
        int m = Integer.parseInt(tokens.nextToken());
        int n = Integer.parseInt(tokens.nextToken());

        int[][] map = new int[n][m];

        for(int x=0; x<n; x++){
            String data = buffer.readLine();
            for(int y=0; y<m; y++){
                map[x][y] = data.charAt(y)-'0';
            }
        }



        List<Node>[][] graph = new List[n][m];



        for(int x=0; x<n; x++){
            for(int y=0; y<m; y++){
                graph[x][y] = new ArrayList<>();
            }
        }


        for(int x=0; x<n; x++){
            for(int y=0; y<m; y++){
                for(int[] d: directions){
                    int nx = x+d[X];
                    int ny = y+d[Y];

                    if(isValid(nx, ny,n,m)){
                        graph[x][y].add(new Node(nx, ny, map[nx][ny]));
                    }

                }
            }
        }

        int[][] dist = new int[n][m];

        for(int x=0; x<n; x++) {
            Arrays.fill(dist[x], MAX_DIST);
        }
        dist[0][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0,0));

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if(dist[current.x][current.y]!=current.cnt)continue;
            
            for(int[] d: directions){
                int nx = current.x+d[X];
                int ny = current.y+d[Y];

                if(isValid(nx, ny,n,m)){

                    int newDist = dist[current.x][current.y] + map[nx][ny];

                    if(newDist<dist[nx][ny]){
                        pq.add(new Node(nx, ny, newDist));
                        dist[nx][ny] = newDist;
                    }

                }
            }
        }

        System.out.println(dist[n-1][m-1]);
    }

    private static boolean isValid(int x, int y, int n, int m){
        return x>=0&&y>=0&&x<n&&y<m;
    }

}

class Node implements Comparable<Node>{
    int x, y, cnt;

    public int compareTo(Node o){
        return this.cnt - o.cnt;
    }

    public Node(int x, int y, int cnt){
        this. x= x;
        this.y = y;
        this.cnt = cnt;
    }

    public String toString(){
        return this.cnt+"->"+this.x+":"+this.y;
    }



}
