import org.w3c.dom.Node;

import java.awt.*;
import java.math.BigInteger;
import java.util.*;
import java.io.*;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    private static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private final static int[][] D = {
            //아래
            {1,0},
            //오른쪽
            {0,1}
    };

    private static final int X = 0;
    private static final int Y = 1;

    private static final Point START = new Point(0,0);
    private static final int NOT_ARRIVAL = -1;


    public static void main(String[] args)throws IOException {
        tokens = new StringTokenizer(buffer.readLine());

        int n = Integer.parseInt(tokens.nextToken());
        int m = Integer.parseInt(tokens.nextToken());

        int[][] map = new int[n][m];

        for(int x=0; x<n; x++){
            tokens = new StringTokenizer(buffer.readLine());
            for(int y=0; y<m; y++){
                map[x][y] = Integer.parseInt(tokens.nextToken());
            }
        }
        Point end = new Point(n-1,m-1);

        int[][] dist = new int[n][m];

        for(int x=0; x<n; x++){
            Arrays.fill(dist[x],NOT_ARRIVAL);
        }
        Deque<Point> que = new ArrayDeque<>();

        dist[START.x][START.y] = 0;
        que.add(START);

        while(!que.isEmpty()){
            Point current = que.poll();

            for(int[] d: D){
                for(int boost=1; boost<=map[current.x][current.y]; boost++){
                    int nx = current.x+boost*d[X];
                    int ny = current.y+boost*d[Y];

                    if(isValid(nx, ny, dist, n,m)){
                        que.add(new Point(nx, ny));
                        dist[nx][ny] = dist[current.x][current.y]+1;
                    }
                }
            }
        }

        System.out.println(dist[end.x][end.y]);





    }

    private static boolean isValid(int nx, int ny, int[][] dist, int n, int m) {
        return inRange(nx, ny, n,m) && dist[nx][ny]==NOT_ARRIVAL;
    }

    private static boolean inRange(int x, int y, int n, int m){
        return x>=0&&y>=0&&x<n&&y<m;
    }

}
class Point{
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


