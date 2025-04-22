

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static final int MAX_CNT = 2501;
    private static final int BLACK = 0;
    private static final int WHITE = 1;

    private static final int X = 0;
    private static final int Y = 1;

    private static final int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};

    public static void main(String[] args)throws IOException {
        tokens = new StringTokenizer(buffer.readLine());
        int n = Integer.parseInt(tokens.nextToken());

        int[][] map = new int[n][n];

        for(int x=0; x<n;x++){
            String data = buffer.readLine();
            for(int y=0; y<n;y++){
                map[x][y] = data.charAt(y)-'0';
            }
        }


        int[][] cnt = new int[n][n];
        for(int x=0; x<n; x++){
            Arrays.fill(cnt[x], MAX_CNT);
        }
        cnt[0][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0,0));

        while(!pq.isEmpty()){
            Node current = pq.poll();

            for(int[] d: directions){
                int nx = current.x+d[X];
                int ny = current.y+d[Y];

                if(isValid(nx, ny, n)){
                    int isBlack = getIsBlack(map, nx, ny,n);
                    int newCnt = cnt[current.x][current.y] + isBlack;

                    if(cnt[nx][ny]>newCnt){
                        cnt[nx][ny] = newCnt;
                        pq.add(new Node(nx, ny, newCnt));
                    }
                }
            }
        }

        System.out.println(cnt[n-1][n-1]);

    }

    private static int getIsBlack(int[][] map, int nx, int ny, int n) {
        if(map[nx][ny]==BLACK){
            return 1;
        }else{
            return 0;
        }
    }

    private static boolean isValid(int x, int y, int n) {
        return x>=0&&y>=0&&x<n&&y<n;

    }
}
class Node implements Comparable<Node>{
    int x, y, changeCnt;

    public Node(int x, int y, int changeCnt){
        this.x = x;
        this.y = y;
        this.changeCnt =changeCnt;
    }

    public int compareTo(Node o){
        return this.changeCnt-o.changeCnt;
    }

}

